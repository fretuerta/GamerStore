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

import com.retuerta.GamerStore.entities.Plataforma;
import com.retuerta.GamerStore.services.PlataformaService;
import com.retuerta.GamerStore.services.TokenService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PlataformaController {

	@Autowired
	private PlataformaService plataformaService;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/plataformas")
	public List<Plataforma> retrieveAllPlataforma(@RequestHeader("Authorization") String token) {

		if (!tokenService.validarToken(token)) { return new ArrayList<Plataforma>(); }

		return plataformaService.getPlataformas();
	}

	@GetMapping("/plataforma/{id}")
	public Plataforma retrievePlataforma(@PathVariable long id,
			@RequestHeader("Authorization") String token) {

		if (!tokenService.validarToken(token)) { return new Plataforma(); }

		return plataformaService.getPlataforma(id);
	}

	@PostMapping("/plataforma")
	public List<Plataforma> createPlataforma(@RequestBody Plataforma plataforma,
			@RequestHeader("Authorization") String token) {

		if (!tokenService.validarToken(token)) { return new ArrayList<Plataforma>(); }

		plataformaService.addPlataforma(plataforma);
		return plataformaService.getPlataformas();
	}

	@PutMapping("/plataforma/{id}")
	public List<Plataforma> updatePlataforma(@RequestBody Plataforma plataforma,
			@PathVariable long id,
			@RequestHeader("Authorization") String token) {

		if (!tokenService.validarToken(token)) { return new ArrayList<Plataforma>(); }

		plataforma.setId(id);
		plataformaService.updatePlataforma(plataforma);
		return plataformaService.getPlataformas();
	}

	@DeleteMapping("/plataforma/{id}")
	public List<Plataforma> deletePlataforma(@PathVariable long id,
			@RequestHeader("Authorization") String token) {

		if (!tokenService.validarToken(token)) { return new ArrayList<Plataforma>(); }

		plataformaService.deletePlataforma(id);
		return plataformaService.getPlataformas();
	}

}
