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

import com.retuerta.GamerStore.entities.Plataforma;
import com.retuerta.GamerStore.services.PlataformaService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PlataformaController {

	@Autowired
	private PlataformaService plataformaService;
	
	@GetMapping("/plataformas")
	public List<Plataforma> retrieveAllPlataforma() {
		return plataformaService.getPlataformas();
	}
	
	@GetMapping("/plataforma/{id}")
	public Plataforma retrievePlataforma(@PathVariable long id) {
		return plataformaService.getPlataforma(id);
	}

	@PostMapping("/plataforma")
	public List<Plataforma> createPlataforma(@RequestBody Plataforma plataforma) {
		plataformaService.addPlataforma(plataforma);
		return plataformaService.getPlataformas();
	}
	
	@PutMapping("/plataforma/{id}")
	public List<Plataforma> updatePlataforma(@RequestBody Plataforma plataforma, @PathVariable long id) {
		plataforma.setId(id);
		plataformaService.updatePlataforma(plataforma);
		return plataformaService.getPlataformas();
	}
	
	@DeleteMapping("/plataforma/{id}")
	public List<Plataforma> deletePlataforma(@PathVariable long id) {
		plataformaService.deletePlataforma(id);
		return plataformaService.getPlataformas();
	}

}
