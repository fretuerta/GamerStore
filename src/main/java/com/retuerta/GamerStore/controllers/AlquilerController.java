package com.retuerta.GamerStore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.DTO.AlquilerDTO;
import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.repositories.AlquilerDetalleRepository;
import com.retuerta.GamerStore.repositories.AlquilerRepository;
import com.retuerta.GamerStore.repositories.ArticuloRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AlquilerController {
	
	@Autowired
	private AlquilerRepository alquilerRepository;
	
	@Autowired
	private AlquilerDetalleRepository alquilerDetalleRepository;
	
	@Autowired
	private ArticuloRepository articuloRepository;
	
	@GetMapping("/alquileres")
	public List<Alquiler> retrieveAllAlquiler() {
		return alquilerRepository.findAll();
	}
	
	@GetMapping("/alquiler/{id}")
	public Alquiler retrieveAlquiler(@PathVariable Long id) {
		Optional<Alquiler> alquiler = alquilerRepository.findById(id);
		return alquiler.get();
	}
	
	@PostMapping("/alquiler")
	public Long createAlquiler(@RequestBody AlquilerDTO alquilerDTO) {

		Alquiler alquiler = new Alquiler();
		alquiler.setCliente(alquilerDTO.getCliente());
		alquiler.setFechaInicio(alquilerDTO.getFechaInicio());
		alquiler.setFechaFin(alquilerDTO.getFechaFin());
		alquiler.setTotal(alquilerDTO.getTotal());
		Alquiler alquilerResult = alquilerRepository.save(alquiler);
		
	  	List<AlquilerDetalle> alquilerDetalles = new ArrayList<AlquilerDetalle>();
		alquilerDetalles = alquilerDTO.getAlquilerDetalles();
		if (alquilerDetalles != null) {
			for (AlquilerDetalle alqDet : alquilerDetalles) {
				alqDet.setAlquiler(alquilerResult);
				alquilerDetalleRepository.save(alqDet);
				
				Articulo articuloTemp = articuloRepository.getOne(alqDet.getArticulo().getId());
				int cantidadDisponibleAlquiler = articuloTemp.getCantDispAlquiler();
				cantidadDisponibleAlquiler -= alqDet.getCantidad();
				if (cantidadDisponibleAlquiler < 0) cantidadDisponibleAlquiler = 0;
				articuloTemp.setCantDispAlquiler(cantidadDisponibleAlquiler);
				articuloRepository.save(articuloTemp);
			}
		}
	
		return alquilerResult.getId();
	}
	
	@PutMapping("/alquiler/{id}")
	public List<Alquiler> updateAlquiler(@RequestBody Alquiler alquiler, @PathVariable Long id) {
		alquiler.setId(id);
		alquilerRepository.save(alquiler);
		return alquilerRepository.findAll();
	}
	
	@DeleteMapping("/alquiler/{id}")
	public List<Alquiler> deleteAlquiler(@PathVariable Long id) {
		alquilerRepository.deleteById(id);
		return alquilerRepository.findAll();
	}

}