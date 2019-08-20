package com.retuerta.GamerStore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.DTO.AlquilerDTO;
import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.services.AlquilerService;
import com.retuerta.GamerStore.services.TokenService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AlquilerController {
	
	@Autowired
	private AlquilerService alquilerService;
	
	@Autowired
	private TokenService tokenService;

	@GetMapping("/alquileres")
	public List<Alquiler> getAlquileres(@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Alquiler>(); }
		
		return alquilerService.getAlquileres();
	}

	@GetMapping("/alquiler/{id}")
	public Alquiler getAlquiler(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new Alquiler(); }
		
		return alquilerService.getAlquiler(id);
	}

	@PostMapping("/alquiler")
	public Long addAlquiler(@RequestBody AlquilerDTO alquilerDTO,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return 0L; }
		
		return alquilerService.addAlquiler(alquilerDTO);
	}

}