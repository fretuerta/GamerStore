package com.retuerta.GamerStore.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantDisponible;
	private Integer cantAlquilados;
	private Float precioVenta;
	private Float precioAlquiler;

	@ManyToOne()
  	private Juego juego;
	
	@ManyToOne()
	private Plataforma plataforma;
	
	private String formato;
	private Date fechaCompra;
	private Date fechaVenta;
	
	public Articulo() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Integer getCantDisponible() { return cantDisponible; }
	public void setCantDisponible(Integer cantidad) { this.cantDisponible = cantidad; }

	public Integer getCantAlquilados() { return cantAlquilados; }
	public void setCantAlquilados(Integer cantidad) { this.cantAlquilados = cantidad; }

	public Juego getJuego() { return juego; }
	public void setJuego(Juego juego) { this.juego = juego; }

	public Plataforma getPlataforma() { return plataforma; }
	public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

	public String getFormato() { return formato; }
	public void setFormato(String formato) { this.formato = formato; }

	public Float getPrecioVenta() { return precioVenta; }
	public void setPrecioVenta(Float precioVenta) { this.precioVenta = precioVenta; }

	public Float getPrecioAlquiler() { return precioAlquiler; }
	public void setPrecioAlquiler(Float precioAlquiler) {this.precioAlquiler = precioAlquiler; }

	public Date getFechaCompra() { return fechaCompra; }
	public void setFechaCompra(Date fechaCompra) { this.fechaCompra = fechaCompra; }

	public Date getFechaVenta() { return fechaVenta; }
	public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }

}
