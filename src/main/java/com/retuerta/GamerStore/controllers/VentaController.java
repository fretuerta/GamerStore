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

import com.retuerta.GamerStore.DTO.VentaDTO;
import com.retuerta.GamerStore.entities.Venta;
import com.retuerta.GamerStore.services.TokenService;
import com.retuerta.GamerStore.services.VentaService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class VentaController {
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private TokenService tokenService;

	@GetMapping("/ventas")
	public List<Venta> getVentas(@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Venta>(); }
		
		return ventaService.getVentas();
	}

	@GetMapping("/venta/{id}")
	public Venta getVenta(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new Venta(); }
		
		return ventaService.getVenta(id);
	}

	@PostMapping("/venta")
	public Long createVenta(@RequestBody VentaDTO ventaDTO,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return 0L; }
		
		return ventaService.addVenta(ventaDTO);
	}

}