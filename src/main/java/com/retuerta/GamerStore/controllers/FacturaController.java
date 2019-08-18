package com.retuerta.GamerStore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.DTO.FacturaDTO;
import com.retuerta.GamerStore.services.AlquilerService;
import com.retuerta.GamerStore.services.FacturaService;
import com.retuerta.GamerStore.services.VentaService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private AlquilerService alquilerService;
	
	@GetMapping("/facturas/{clienteId}")
	public List<FacturaDTO> retrieveFacturasCliente(@PathVariable Long clienteId) {
		return facturaService.getFacturasCliente(clienteId);
		
	}
	
	@GetMapping("/facturas/pdf/tipo/{tipo}/id/{id}")
	public byte[] getFacturaPDF(@PathVariable char tipo,@PathVariable Long id) {
		FacturaService facturaService = new FacturaService();
		return facturaService.generateReport(id, tipo);
	}
	
	@GetMapping("/facturas/det/{clienteId}")
	public List<FacturaDTO> retrieveFacturasDetCliente(@PathVariable Long clienteId) {
		return facturaService.getFacturasDetCliente(clienteId);
	}
	
	@GetMapping("/factura/det/{numFactura}")
	public FacturaDTO retrieveFacturaDet(@PathVariable String numFactura) {
		return facturaService.getFacturaDet(numFactura);
	}
	
	@GetMapping("/facturas/delete/{numFacturaString}")
	public String deleteFactura(@PathVariable String numFacturaString) {
		
		if (numFacturaString.length() < 2) { return "N"; }

		Long id = Long.parseLong(numFacturaString.substring(1));
		char tipo = numFacturaString.charAt(0);
		
		if (tipo == 'A') { alquilerService.deleteAlquiler(id); }
		if (tipo == 'V') { ventaService.deleteVenta(id); }
		
		return "S";
	}
	
}
