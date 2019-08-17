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

import com.retuerta.GamerStore.entities.Cliente;
import com.retuerta.GamerStore.services.ClienteService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> getClientes() {
		return clienteService.getClientes();
	}

	@GetMapping("/cliente/{id}")
	public Cliente getCliente(@PathVariable Long id) {
		return clienteService.getCliente(id);
	}

	@PostMapping("/cliente")
	public List<Cliente> addCliente(@RequestBody Cliente cliente) {
		clienteService.addCliente(cliente);
		return clienteService.getClientes();
	}

	@PutMapping("/cliente/{id}")
	public List<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		cliente.setId(id);
		clienteService.updateCliente(cliente);
		return clienteService.getClientes();
	}

	@DeleteMapping("/cliente/{id}")
	public List<Cliente> deleteCliente(@PathVariable Long id) {
		clienteService.deleteCliente(id);
		return clienteService.getClientes();
	}

}