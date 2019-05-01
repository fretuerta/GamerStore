package com.retuerta.GamerStore.controllers;

import java.util.ArrayList;
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

import com.retuerta.GamerStore.DTO.VentaDTO;
import com.retuerta.GamerStore.entities.Venta;
import com.retuerta.GamerStore.entities.VentaDetalle;
import com.retuerta.GamerStore.repositories.VentaDetalleRepository;
import com.retuerta.GamerStore.repositories.VentaRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class VentaController {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;
	
	@GetMapping("/ventas")
	public List<Venta> retrieveAllVenta() {
		return ventaRepository.findAll();
	}
	
	@GetMapping("/venta/{id}")
	public Venta retrieveVenta(@PathVariable Long id) {
		Optional<Venta> venta = ventaRepository.findById(id);
		return venta.get();
	}
	
	@PostMapping("/venta")
	public Long createVenta(@RequestBody VentaDTO ventaDTO) {

		Venta venta = new Venta();
		venta.setCliente(ventaDTO.getCliente());
		venta.setFechaVenta(ventaDTO.getFechaVenta());
		Venta ventaResult = ventaRepository.save(venta);
		
	  	List<VentaDetalle> ventaDetalles = new ArrayList<VentaDetalle>();
	  	ventaDetalles = ventaDTO.getVentaDetalles();
		if (ventaDetalles != null) {
			for (VentaDetalle vntDet : ventaDetalles) {
				vntDet.setVenta(ventaResult);
				ventaDetalleRepository.save(vntDet);
			}		
		}
	
		return ventaResult.getId();
	}
	
	@PutMapping("/venta/{id}")
	public List<Venta> updateVenta(@RequestBody Venta venta, @PathVariable Long id) {
		venta.setId(id);
		ventaRepository.save(venta);
		return ventaRepository.findAll();
	}
	
	@DeleteMapping("/venta/{id}")
	public List<Venta> deleteVenta(@PathVariable Long id) {
		ventaRepository.deleteById(id);
		return ventaRepository.findAll();
	}

}