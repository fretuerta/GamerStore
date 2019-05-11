package com.retuerta.GamerStore.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Cliente;
import com.retuerta.GamerStore.entities.VentaDetalle;

public class FacturaDTO {

	private String numFactura;
	private Cliente cliente;
	private Date fechaFactura;
	private float total;
  	private List<FacturaDetalleDTO> facturaDetalles;
	
	public FacturaDTO() {}
	
	public String getNumFactura() { return numFactura; }
	public void setNumFactura(String numFactura) { this.numFactura = numFactura; }
	
	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }
	
	public Date getFechaFactura() { return fechaFactura; }
	public void setFechaFactura(Date fechaFactura) { this.fechaFactura = fechaFactura; }
	
	public float getTotal() {return total;}
	public void setTotal(float total) { this.total = total;}

	public List<FacturaDetalleDTO> getVentaDetalles() {
		return facturaDetalles;
	}

	public void setVentaDetalles(List<FacturaDetalleDTO> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}
	
	public void setFromAlquilerDTO(AlquilerDTO alquilerDTO) {
		this.numFactura = "A" + alquilerDTO.getId();
		this.cliente = alquilerDTO.getCliente();
		this.fechaFactura = alquilerDTO.getFechaInicio();
		this.total = alquilerDTO.getTotal();
		List<AlquilerDetalle> alquilerDetalles = alquilerDTO.getAlquilerDetalles();
		this.facturaDetalles = new ArrayList<FacturaDetalleDTO>();
		if (alquilerDetalles != null) {
			for (AlquilerDetalle alqDet : alquilerDetalles) {
				FacturaDetalleDTO facDetDTO = new FacturaDetalleDTO(alqDet);
				this.facturaDetalles.add(facDetDTO);
			}
		}
	}
	
	public void setFromVentaDTO(VentaDTO ventaDTO) {
		this.numFactura = "V" + ventaDTO.getId();
		this.cliente = ventaDTO.getCliente();
		this.fechaFactura = ventaDTO.getFechaVenta();
		this.total = ventaDTO.getTotal();
		List<VentaDetalle> ventaDetalles = ventaDTO.getVentaDetalles();
		this.facturaDetalles = new ArrayList<FacturaDetalleDTO>();
		if (ventaDetalles != null) {
			for (VentaDetalle vtaDet : ventaDetalles) {
				FacturaDetalleDTO facDetDTO = new FacturaDetalleDTO(vtaDet);
				this.facturaDetalles.add(facDetDTO);
			}
		}
	}
}
