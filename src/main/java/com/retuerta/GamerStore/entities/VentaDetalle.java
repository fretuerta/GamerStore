package com.retuerta.GamerStore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ventaDetalle")
public class VentaDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	private Articulo articulo;
	
	@ManyToOne()
	private Venta venta;
	
	private int cantidad;
	private double precio;
	
	public VentaDetalle() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Articulo getArticulo() { return articulo; }
	public void setArticulo(Articulo articulo) { this.articulo = articulo; }
	
	public Venta getVenta() { return venta; }
	public void setVenta(Venta venta) { this.venta = venta; }
	
	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }
	
	public double getPrecio() { return precio; }
	public void setPrecio(double precio) { this.precio = precio; }
	
}