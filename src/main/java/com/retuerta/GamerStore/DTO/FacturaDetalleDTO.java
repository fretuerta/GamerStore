package com.retuerta.GamerStore.DTO;

import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.entities.VentaDetalle;

public class FacturaDetalleDTO {

	private Long id;
	private Articulo articulo;
	private int cantidad;
	private float precio;
	
	public FacturaDetalleDTO() {}
	public FacturaDetalleDTO(AlquilerDetalle alquilerDetalle) {
		this.id = alquilerDetalle.getId();
		this.articulo = alquilerDetalle.getArticulo();
		this.cantidad = alquilerDetalle.getCantidad();
		this.precio = alquilerDetalle.getPrecio();
	}
	public FacturaDetalleDTO(VentaDetalle ventaDetalle) {
		this.id = ventaDetalle.getId();
		this.articulo = ventaDetalle.getArticulo();
		this.cantidad = ventaDetalle.getCantidad();
		this.precio = ventaDetalle.getPrecio();
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Articulo getArticulo() { return articulo; }
	public void setArticulo(Articulo articulo) { this.articulo = articulo; }
	
	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }
	
	public float getPrecio() { return precio; }
	public void setPrecio(float precio) { this.precio = precio; }
	
}
