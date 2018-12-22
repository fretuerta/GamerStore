package com.retuerta.GamerStore.controllers;

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

import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.repositories.ArticuloRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticuloController {
	
	@Autowired
	private ArticuloRepository articuloRepository;
	
	@GetMapping("/articulos")
	public List<Articulo> retrieveAllArticulo() {
		return articuloRepository.findAll();
	}
	
	@GetMapping("/articulo/{id}")
	public Articulo retrieveArticulo(@PathVariable Long id) {
		Optional<Articulo> articulo = articuloRepository.findById(id);
		return articulo.get();
	}
	
	@PostMapping("/articulo")
	public List<Articulo> createArticulo(@RequestBody Articulo articulo) {
		articuloRepository.save(articulo);
		return articuloRepository.findAll();
	}
	
	@PutMapping("/articulo/{id}")
	public List<Articulo> updateArticulo(@RequestBody Articulo articulo, @PathVariable Long id) {
		articulo.setId(id);
		articuloRepository.save(articulo);
		return articuloRepository.findAll();
	}
	
	@DeleteMapping("/articulo/{id}")
	public List<Articulo> deleteArticulo(@PathVariable Long id) {
		articuloRepository.deleteById(id);
		return articuloRepository.findAll();
	}

}
