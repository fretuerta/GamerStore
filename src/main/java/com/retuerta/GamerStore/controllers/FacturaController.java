package com.retuerta.GamerStore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.DTO.AlquilerDTO;
import com.retuerta.GamerStore.DTO.FacturaDTO;
import com.retuerta.GamerStore.DTO.VentaDTO;
import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Venta;
import com.retuerta.GamerStore.entities.VentaDetalle;
import com.retuerta.GamerStore.repositories.AlquilerDetalleRepository;
import com.retuerta.GamerStore.repositories.AlquilerRepository;
import com.retuerta.GamerStore.repositories.VentaDetalleRepository;
import com.retuerta.GamerStore.repositories.VentaRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FacturaController {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;
	
	@Autowired
	private AlquilerRepository alquilerRepository;
	
	@Autowired
	private AlquilerDetalleRepository alquilerDetalleRepository;
	
	@GetMapping("/facturas/{clienteId}")
	public List<FacturaDTO> retrieveFacturasCliente(@PathVariable Long clienteId) {
		List<FacturaDTO> facturasList = new ArrayList<FacturaDTO>();
		
		List<Alquiler> alquileres = alquilerRepository.findAll();
		List<Venta> ventas = ventaRepository.findAll();

		for (Alquiler alq : alquileres) {
			if (alq.getCliente() != null && alq.getCliente().getId() == clienteId ) {
				AlquilerDTO alquilerDTO = new AlquilerDTO(alq);
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromAlquilerDTO(alquilerDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		for (Venta vts : ventas) {
			if (vts.getCliente() != null && vts.getCliente().getId() == clienteId ) {
				VentaDTO ventaDTO = new VentaDTO(vts);
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromVentaDTO(ventaDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		return facturasList;
		
	}
	
	@GetMapping("/facturas/det/{clienteId}")
	public List<FacturaDTO> retrieveFacturasDetCliente(@PathVariable Long clienteId) {
		List<FacturaDTO> facturasList = new ArrayList<FacturaDTO>();
		
		List<Alquiler> alquileres = alquilerRepository.findAll();
		List<AlquilerDetalle> alquilerDetalleListTODOS = alquilerDetalleRepository.findAll();
		List<Venta> ventas = ventaRepository.findAll();
		List<VentaDetalle> ventaDetalleListTODOS = ventaDetalleRepository.findAll();
		
		for (Alquiler alq : alquileres) {
			if (alq.getCliente().getId() == clienteId ) {
				AlquilerDTO alquilerDTO = new AlquilerDTO(alq);
				for (AlquilerDetalle alqDet : alquilerDetalleListTODOS) {
					if (alqDet.getAlquiler().equals(alq)) {
						alquilerDTO.addAlquilerDetalle(alqDet);
					}
				}
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromAlquilerDTO(alquilerDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		for (Venta vts : ventas) {
			if (vts.getCliente().getId() == clienteId ) {
				VentaDTO ventaDTO = new VentaDTO(vts);
				for (VentaDetalle vtaDet : ventaDetalleListTODOS) {
					if (vtaDet.getVenta() .equals(vts)) {
						ventaDTO.addVentaDetalle(vtaDet);
					}
					
				}
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromVentaDTO(ventaDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		return facturasList;
	}
	
	@GetMapping("/factura/det/{numFactura}")
	public FacturaDTO retrieveFacturaDet(@PathVariable String numFactura) {
		
		FacturaDTO facturaDTO = new FacturaDTO();
		
		if (numFactura.length() < 2) { return facturaDTO; }
		
		Long id = Long.parseLong(numFactura.substring(1));
		
		if (numFactura.charAt(0) == 'A') {
			Optional<Alquiler> alquiler = alquilerRepository.findById(id);
			AlquilerDTO alquilerDTO = new AlquilerDTO(alquiler.get());
		  	List<AlquilerDetalle> alquilerDetallesList = new ArrayList<AlquilerDetalle>();
		  	alquilerDetallesList = alquilerDetalleRepository.findAll();
			for (AlquilerDetalle alqDet : alquilerDetallesList) {
				if (alqDet.getAlquiler().equals(alquiler.get())) {
					alquilerDTO.addAlquilerDetalle(alqDet);
				}
			}
			facturaDTO.setFromAlquilerDTO(alquilerDTO);
		}
		
		if (numFactura.charAt(0) == 'V') {
			Optional<Venta> venta = ventaRepository.findById(id);
			VentaDTO ventaDTO = new VentaDTO(venta.get());
			List<VentaDetalle> ventaDetallesList = new ArrayList<VentaDetalle>();
			ventaDetallesList = ventaDetalleRepository.findAll();
			for (VentaDetalle vtaDet : ventaDetallesList) {
				if (vtaDet.getVenta().equals(venta.get())) {
					ventaDTO.addVentaDetalle(vtaDet);
				}
			}
			facturaDTO.setFromVentaDTO(ventaDTO);
		}

		return facturaDTO;
	}
	

}
