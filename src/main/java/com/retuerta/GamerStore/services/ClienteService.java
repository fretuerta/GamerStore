package com.retuerta.GamerStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.entities.Cliente;
import com.retuerta.GamerStore.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private AlquilerService alquilerService;
	
	public List<Cliente> getClientes() {
		return clienteRepository.findAll();
	}
	
	public Cliente getCliente(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}
	
	public Cliente addCliente(Cliente cliente) {
		cliente.setId(null);
		Cliente clienteResult = clienteRepository.save(cliente);
		return clienteResult;
	}
	
	public Cliente updateCliente(Cliente cliente) {
		Cliente clienteResult = clienteRepository.save(cliente);
		return clienteResult;
	}
	
	public boolean deleteCliente(Long id) {
		
		ventaService.deleteVentaClienteId(id);
		alquilerService.deleteAlquilerClienteId(id);
		
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
