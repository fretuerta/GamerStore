package com.retuerta.GamerStore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.DTO.AlquilerDTO;
import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.services.AlquilerService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AlquilerController {
	
	@Autowired
	private AlquilerService alquilerService;

	@GetMapping("/alquileres")
	public List<Alquiler> getAlquileres() {
		return alquilerService.getAlquileres();
	}

	@GetMapping("/alquiler/{id}")
	public Alquiler getAlquiler(@PathVariable Long id) {
		return alquilerService.getAlquiler(id);
	}

	@PostMapping("/alquiler")
	public Long addAlquiler(@RequestBody AlquilerDTO alquilerDTO) {
		return alquilerService.addAlquiler(alquilerDTO);
	}

}