package com.retuerta.GamerStore.DTO;

import java.util.List;

import com.retuerta.GamerStore.entities.Venta;
import com.retuerta.GamerStore.entities.VentaDetalle;

public class VentaDTO extends Venta {

  	private List<VentaDetalle> ventaDetalles;
	
	public VentaDTO() {super();}

	public List<VentaDetalle> getVentaDetalles() {
		return ventaDetalles;
	}

	public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
		this.ventaDetalles = ventaDetalles;
	}

}