package com.retuerta.GamerStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.repositories.AlquilerDetalleRepository;
import com.retuerta.GamerStore.repositories.AlquilerRepository;
import com.retuerta.GamerStore.repositories.ArticuloRepository;
import com.retuerta.GamerStore.DTO.AlquilerDTO;
import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Articulo;

@Service
public class AlquilerService {
	
	@Autowired
	private AlquilerRepository alquilerRepository;
	
	@Autowired
	private AlquilerDetalleRepository alquilerDetalleRepository;
	
	@Autowired
	private ArticuloRepository articuloRepository;
	
	public List<Alquiler> getAlquileres() {
		return alquilerRepository.findAll();
	}

	public Alquiler getAlquiler(Long id) {
		Alquiler alquiler = null;
		if (alquilerRepository.existsById(id)) {
			alquiler = alquilerRepository.findById(id).get();
		}
		return alquiler;
	}
	
	public Long addAlquiler(AlquilerDTO alquilerDTO) {

		Alquiler alquiler = new Alquiler();
		alquiler.setCliente(alquilerDTO.getCliente());
		alquiler.setFechaInicio(alquilerDTO.getFechaInicio());
		alquiler.setFechaFin(alquilerDTO.getFechaFin());
		alquiler.setTotal(alquilerDTO.getTotal());
		alquiler = alquilerRepository.save(alquiler);
		double total = 0.0;
		
	  	List<AlquilerDetalle> alquilerDetalles = new ArrayList<AlquilerDetalle>();
		alquilerDetalles = alquilerDTO.getAlquilerDetalles();
		if (alquilerDetalles != null) {
			for (AlquilerDetalle alqDet : alquilerDetalles) {
				alqDet.setAlquiler(alquiler);
				
				Articulo articuloTemp = articuloRepository.getOne(alqDet.getArticulo().getId());
				int cantidadDisponibleAlquiler = articuloTemp.getCantDispAlquiler();
				cantidadDisponibleAlquiler -= alqDet.getCantidad();
				if (cantidadDisponibleAlquiler < 0) cantidadDisponibleAlquiler = 0;
				articuloTemp.setCantDispAlquiler(cantidadDisponibleAlquiler);
				articuloRepository.save(articuloTemp);
				
				total += alqDet.getCantidad() * articuloTemp.getPrecioAlquiler();
			}
		}
	
		alquilerDetalleRepository.saveAll(alquilerDetalles);
		
		alquiler.setTotal(total);
		alquiler = alquilerRepository.save(alquiler);
		
		return alquiler.getId();
	}
	
	public Alquiler updateAlquiler(Alquiler alquiler) {
		return alquilerRepository.save(alquiler);
	}
	
	public boolean deleteAlquiler(Long id) {
		
		List<AlquilerDetalle> alquilerDetalles = alquilerDetalleRepository.findAll();
		for (AlquilerDetalle alqDet : alquilerDetalles) {
			if (alqDet.getAlquiler().getId() == id) {
				Articulo articulo = alqDet.getArticulo();
				articulo.setCantDispAlquiler(articulo.getCantDispAlquiler() + alqDet.getCantidad());
				articuloRepository.save(articulo);
				alquilerDetalleRepository.deleteById(alqDet.getId());
			}
		}

		if (alquilerRepository.existsById(id)) {
			alquilerRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean deleteAlquilerClienteId(Long clienteId) {
		
		List<Alquiler> alquileres = alquilerRepository.findAllClienteId(clienteId);
		for (Alquiler alquiler : alquileres) {
			deleteAlquiler(alquiler.getId());
		}
		return true;
	}
	
}
