package com.retuerta.GamerStore.controllers;

import java.net.URISyntaxException;
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

import com.retuerta.GamerStore.entities.Plataforma;
import com.retuerta.GamerStore.repositories.PlataformaRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PlataformaController {

	@Autowired
	private PlataformaRepository plataformaRepository;
	
	@GetMapping("/plataformas")
	public List<Plataforma> retrieveAllPlataforma() {
		//return plataformaRepository.findAll();
		return plataformaRepository.findAllByOrderByNombreAsc();
	}
	
	@GetMapping("/plataforma/{id}")
	public Plataforma retrievePlataforma(@PathVariable long id) {
		Optional<Plataforma> plataforma = plataformaRepository.findById(id);
		return plataforma.get();
	}
	
	@PostMapping("/plataforma")
	public List<Plataforma> createPlataforma(@RequestBody Plataforma plataforma) throws URISyntaxException {
		plataformaRepository.save(plataforma);
		return plataformaRepository.findAllByOrderByNombreAsc();
	}
	
	@PutMapping("/plataforma/{id}")
	public List<Plataforma> updatePlataforma(@RequestBody Plataforma plataforma, @PathVariable long id) {
		plataforma.setId(id);
		plataformaRepository.save(plataforma);
		return plataformaRepository.findAllByOrderByNombreAsc();
	}
	
	@DeleteMapping("/plataforma/{id}")
	public List<Plataforma> deleteStudent(@PathVariable long id) {
		plataformaRepository.deleteById(id);
		return plataformaRepository.findAllByOrderByNombreAsc();
	}
	
	// EJEMPLOS
	@GetMapping("/plataforma/nombre/{nombre}")
	public List<Plataforma> retrievePlataformaPorNombre(@PathVariable String nombre) {
		List<Plataforma> plataforma = plataformaRepository.findAllPlataformasNombreContiene(nombre);
		return plataforma;
	}
}
