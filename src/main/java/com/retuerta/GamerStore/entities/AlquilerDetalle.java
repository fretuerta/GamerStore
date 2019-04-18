package com.retuerta.GamerStore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alquilerDetalle")
public class AlquilerDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	private Articulo articulo;
	
	@ManyToOne()
	private Alquiler alquiler;
	
	private int cantidad;
	private float precio;
	
	public AlquilerDetalle() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Articulo getArticulo() { return articulo; }
	public void setArticulo(Articulo articulo) { this.articulo = articulo; }
	
	public Alquiler getAlquiler() { return alquiler; }
	public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }
	
	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }
	
	public float getPrecio() { return precio; }
	public void setPrecio(float precio) { this.precio = precio; }
	
}