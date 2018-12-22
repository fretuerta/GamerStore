package com.retuerta.GamerStore.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.retuerta.GamerStore.entities.Juego;
import com.retuerta.GamerStore.repositories.JuegoRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class JuegoController {
	
	@Autowired
	private JuegoRepository juegoRepository;

	@GetMapping("/juegos")
	public List<Juego> retrieveAllJuego() {
		//return plataformaRepository.findAll();
		return juegoRepository.findAllByOrderByNombreAsc();
	}
	
	@GetMapping("/juego/{id}")
	public Juego retrieveJuego(@PathVariable Long id) {
		Optional<Juego> juego = juegoRepository.findById(id);
		return juego.get();
	}
	
	@PostMapping("/juego")
	public List<Juego> createJuego(@Valid @RequestBody Juego juego) {
		juegoRepository.save(juego);
		return juegoRepository.findAllByOrderByNombreAsc();
	}
	
	@PutMapping("/juego/{id}")
	public List<Juego> updateJuego(@Valid @RequestBody Juego juego, @PathVariable Long id) {
		juego.setId(id);
		juegoRepository.save(juego);
		return juegoRepository.findAllByOrderByNombreAsc();
	}
	
	@DeleteMapping("/juego/{id}")
	public List<Juego> deleteJuego(@PathVariable Long id) {
		juegoRepository.deleteById(id);
		return juegoRepository.findAllByOrderByNombreAsc();
	}
}
