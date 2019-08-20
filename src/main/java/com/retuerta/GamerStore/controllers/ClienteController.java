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

import com.retuerta.GamerStore.entities.Cliente;
import com.retuerta.GamerStore.services.ClienteService;
import com.retuerta.GamerStore.services.TokenService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TokenService tokenService;

	@GetMapping("/clientes")
	public List<Cliente> getClientes(@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Cliente>(); }
		
		return clienteService.getClientes();
	}

	@GetMapping("/cliente/{id}")
	public Cliente getCliente(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new Cliente(); }
		
		return clienteService.getCliente(id);
	}

	@PostMapping("/cliente")
	public List<Cliente> addCliente(@RequestBody Cliente cliente,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Cliente>(); }
		
		clienteService.addCliente(cliente);
		return clienteService.getClientes();
	}

	@PutMapping("/cliente/{id}")
	public List<Cliente> updateCliente(@RequestBody Cliente cliente,
			@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Cliente>(); }
		
		cliente.setId(id);
		clienteService.updateCliente(cliente);
		return clienteService.getClientes();
	}

	@DeleteMapping("/cliente/{id}")
	public List<Cliente> deleteCliente(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		
		if (!tokenService.validarToken(token)) { return new ArrayList<Cliente>(); }
		
		clienteService.deleteCliente(id);
		return clienteService.getClientes();
	}

}