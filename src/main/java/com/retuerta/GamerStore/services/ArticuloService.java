package com.retuerta.GamerStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.repositories.ArticuloRepository;

@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;
	
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
		
		if (articuloRepository.existsById(id)) {
			articuloRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
