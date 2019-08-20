package com.retuerta.GamerStore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.services.ArticuloService;
import com.retuerta.GamerStore.services.TokenService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticuloController {
	
	@Autowired
	private ArticuloService	articuloService;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/articulos")
	public List<Articulo> retrieveAllArticulo(@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Articulo>(); }
		
		return articuloService.getArticulos();
	}
	
	@GetMapping("/articulos/list")
	public List<Articulo> retrieveAllArticuloSinCaratula(@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Articulo>(); }
		
		return articuloService.getArticulosSinCaratula();
	}

	@GetMapping("/articulo/{id}")
	public Articulo retrieveArticulo(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new Articulo(); }
		
		return articuloService.getArticulo(id);
	}

	@PostMapping("/articulo")
	public List<Articulo> createArticulo(@RequestBody Articulo articulo,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Articulo>(); }
		
		articuloService.addArticulo(articulo);
		return articuloService.getArticulosSinCaratula();
	}
	
	@PutMapping("/articulo/{id}")
	public List<Articulo> updateArticulo(@RequestBody Articulo articulo,
			@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Articulo>(); }
		
		articulo.setId(id);
		articuloService.updateArticulo(articulo);
		return articuloService.getArticulosSinCaratula();
	}

	@DeleteMapping("/articulo/{id}")
	public List<Articulo> deleteArticulo(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Articulo>(); }
		
		articuloService.deleteArticulo(id);
		return articuloService.getArticulosSinCaratula();
	}

}
