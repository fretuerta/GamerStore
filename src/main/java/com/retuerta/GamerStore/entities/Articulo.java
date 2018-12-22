package com.retuerta.GamerStore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	private Float precioVenta;
	private Float precioAlquiler;
	
  	private Long juegoId;
	private Long plataformaId;
	
	private String formato;
	
	public Articulo() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Integer getCantidad() { return cantidad; }
	public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

	public Long getJuegoId() { return juegoId; }
	public void setJuegoId(Long juegoId) { this.juegoId = juegoId; }

	public Long getPlataformaId() { return plataformaId; }
	public void setPlataformaId(Long plataformaId) { this.plataformaId = plataformaId; }

	public String getFormato() { return formato; }
	public void setFormato(String formato) { this.formato = formato; }

	public Float getPrecioVenta() { return precioVenta; }
	public void setPrecioVenta(Float precioVenta) { this.precioVenta = precioVenta; }

	public Float getPrecioAlquiler() { return precioAlquiler; }
	public void setPrecioAlquiler(Float precioAlquiler) {this.precioAlquiler = precioAlquiler; }

}
