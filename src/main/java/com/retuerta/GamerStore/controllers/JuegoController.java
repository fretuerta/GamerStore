package com.retuerta.GamerStore.controllers;

import java.util.List;

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
import com.retuerta.GamerStore.services.JuegoService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class JuegoController {
	
	@Autowired
	private JuegoService juegoService;

	@GetMapping("/juegos")
	public List<Juego> retrieveAllJuego() {
		return juegoService.getJuegos();
	}

	@GetMapping("/juego/{id}")
	public Juego retrieveJuego(@PathVariable Long id) {
		return juegoService.getJuego(id);
	}

	@PostMapping("/juego")
	public List<Juego> addJuego(@Valid @RequestBody Juego juego) {
		juegoService.addJuego(juego);
		return juegoService.getJuegos();
	}

	@PutMapping("/juego/{id}")
	public List<Juego> updateJuego(@Valid @RequestBody Juego juego, @PathVariable Long id) {
		juego.setId(id);
		juegoService.updateJuego(juego);
		return juegoService.getJuegos();
	}

	@DeleteMapping("/juego/{id}")
	public List<Juego> deleteJuego(@PathVariable Long id) {
		juegoService.deleteJuego(id);
		return juegoService.getJuegos();
	}

}
