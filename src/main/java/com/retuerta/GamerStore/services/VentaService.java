package com.retuerta.GamerStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.DTO.VentaDTO;
import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.entities.Venta;
import com.retuerta.GamerStore.entities.VentaDetalle;
import com.retuerta.GamerStore.repositories.ArticuloRepository;
import com.retuerta.GamerStore.repositories.VentaDetalleRepository;
import com.retuerta.GamerStore.repositories.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;
	
	@Autowired
	private ArticuloRepository articuloRepository;
	

	public List<Venta> getVentas() {
		return ventaRepository.findAll();
	}
	
	public Venta getVenta(Long id) {
		Venta venta = null;
		if (ventaRepository.existsById(id)) {
			venta = ventaRepository.findById(id).get();
		}
		return venta;
	}
	
	public Long addVenta(VentaDTO ventaDTO) {

		Venta venta = new Venta();
		venta.setCliente(ventaDTO.getCliente());
		venta.setFechaVenta(ventaDTO.getFechaVenta());
		venta.setTotal(ventaDTO.getTotal());
		venta = ventaRepository.save(venta);
		double total = 0.0;
		
	  	List<VentaDetalle> ventaDetalles = new ArrayList<VentaDetalle>();
	  	ventaDetalles = ventaDTO.getVentaDetalles();
		if (ventaDetalles != null) {
			for (VentaDetalle vntDet : ventaDetalles) {
				vntDet.setVenta(venta);
				
				Articulo articuloTemp = articuloRepository.getOne(vntDet.getArticulo().getId());
				int cantidadDisponibleVenta = articuloTemp.getCantDispVenta();
				cantidadDisponibleVenta -= vntDet.getCantidad();
				if (cantidadDisponibleVenta < 0) cantidadDisponibleVenta = 0;
				articuloTemp.setCantDispVenta(cantidadDisponibleVenta);
				articuloRepository.save(articuloTemp);
				
				total += vntDet.getCantidad() * articuloTemp.getPrecioVenta();
			}		
		}
		ventaDetalleRepository.saveAll(ventaDetalles);
		
		venta.setTotal(total);
		venta = ventaRepository.save(venta);
	
		return venta.getId();
	}
	
	public Venta updateVenta(Venta venta) {
		Venta ventaResult = ventaRepository.save(venta);
		return ventaResult;
	}
	
	public boolean deleteVenta(Long id) {
		if (ventaRepository.existsById(id)) {
			ventaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
