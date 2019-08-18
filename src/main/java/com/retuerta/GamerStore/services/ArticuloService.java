package com.retuerta.GamerStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.entities.VentaDetalle;
import com.retuerta.GamerStore.repositories.AlquilerDetalleRepository;
import com.retuerta.GamerStore.repositories.ArticuloRepository;
import com.retuerta.GamerStore.repositories.VentaDetalleRepository;

@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;
	
	@Autowired
	private AlquilerDetalleRepository alquilerDetalleRepository;
	
	public List<Articulo> getArticulos() {
		return articuloRepository.findAll();
	}
	
	public List<Articulo> getArticulosSinCaratula() {
		List<Articulo> arts = articuloRepository.findAll();
		for (Articulo art : arts) {
			art.getJuego().setCaratula(null);
		}
		return arts;
	}
	
	public Articulo getArticulo(Long id) {
		Optional<Articulo> articulo = articuloRepository.findById(id);
		return articulo.get();
	}
	
	public Articulo getArticuloSinCaratula(Long id) {
		Articulo articulo = articuloRepository.findById(id).get();
		articulo.getJuego().setCaratula(null);
		return articulo;
	}

	public Articulo addArticulo(Articulo articulo) {
		articulo.setId(null);
		return articuloRepository.save(articulo);
	}
	
	public Articulo updateArticulo(Articulo articulo) {
		return articuloRepository.save(articulo);
	}
	
	public boolean deleteArticulo(Long id) {
		
		List<VentaDetalle> ventaDetalles = ventaDetalleRepository.findAllArticuloId(id);
		for (VentaDetalle ventaDetalle : ventaDetalles) {
			ventaDetalleRepository.deleteById(ventaDetalle.getId());
		}
		
		List<AlquilerDetalle> alquilerDetalles = alquilerDetalleRepository.findAllArticuloId(id);
		for (AlquilerDetalle alquilerDetalle : alquilerDetalles) {
			alquilerDetalleRepository.deleteById(alquilerDetalle.getId());
		}
		
		if (articuloRepository.existsById(id)) {
			articuloRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean deleteArticuloPlataformaId(Long plataformaId) {

		List<Articulo> articulos = articuloRepository.findAllPlataformaId(plataformaId);
		for (Articulo articulo : articulos) {
			deleteArticulo(articulo.getId());
		}

		return true;
	}
	
	public boolean deleteArticuloJuegoId(Long juegoId) {
		
		List<Articulo> articulos = articuloRepository.findAllJuegoId(juegoId);
		for (Articulo articulo : articulos) {
			deleteArticulo(articulo.getId());
		}
		return true;
	}
	
}
