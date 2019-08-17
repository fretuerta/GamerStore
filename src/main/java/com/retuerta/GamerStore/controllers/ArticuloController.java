package com.retuerta.GamerStore.controllers;

import java.util.List;

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
import com.retuerta.GamerStore.services.ArticuloService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticuloController {
	
	@Autowired
	private ArticuloService	articuloService;
	
	@GetMapping("/articulos")
	public List<Articulo> retrieveAllArticulo() {
		return articuloService.getArticulos();
	}
	
	@GetMapping("/articulos/list")
	public List<Articulo> retrieveAllArticuloSinCaratula() {
		return articuloService.getArticulosSinCaratula();
	}

	@GetMapping("/articulo/{id}")
	public Articulo retrieveArticulo(@PathVariable Long id) {
		return articuloService.getArticulo(id);
	}

	@PostMapping("/articulo")
	public List<Articulo> createArticulo(@RequestBody Articulo articulo) {
		articuloService.addArticulo(articulo);
		return articuloService.getArticulosSinCaratula();
	}
	
	@PutMapping("/articulo/{id}")
	public List<Articulo> updateArticulo(@RequestBody Articulo articulo, @PathVariable Long id) {
		articulo.setId(id);
		articuloService.updateArticulo(articulo);
		return articuloService.getArticulosSinCaratula();
	}

	@DeleteMapping("/articulo/{id}")
	public List<Articulo> deleteArticulo(@PathVariable Long id) {
		articuloService.deleteArticulo(id);
		return articuloService.getArticulosSinCaratula();
	}

}
