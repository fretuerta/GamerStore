package com.retuerta.GamerStore.controllers;

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

import com.retuerta.GamerStore.entities.Cliente;
import com.retuerta.GamerStore.repositories.ClienteRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> retrieveAllCliente() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	public Cliente retrieveCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}
	
	@PostMapping("/cliente")
	public List<Cliente> createCliente(@RequestBody Cliente cliente) {
		clienteRepository.save(cliente);
		return clienteRepository.findAll();
	}
	
	@PutMapping("/cliente/{id}")
	public List<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		cliente.setId(id);
		clienteRepository.save(cliente);
		return clienteRepository.findAll();
	}
	
	@DeleteMapping("/cliente/{id}")
	public List<Cliente> deleteCliente(@PathVariable Long id) {
		clienteRepository.deleteById(id);
		return clienteRepository.findAll();
	}

}