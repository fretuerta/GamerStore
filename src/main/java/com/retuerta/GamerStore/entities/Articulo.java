package com.retuerta.GamerStore.entities;

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
	
	private Integer cantDispVenta;
	private Integer cantDispAlquiler;
	private Float precioVenta;
	private Float precioAlquiler;
	private String codigo;

	@ManyToOne()
  	private Juego juego;
	
	@ManyToOne()
	private Plataforma plataforma;
	
	private String formato;
	
	public Articulo() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Integer getCantDispVenta() { return cantDispVenta; }
	public void setCantDispVenta(Integer cantidad) { this.cantDispVenta = cantidad; }

	public Integer getCantDispAlquiler() { return cantDispAlquiler; }
	public void setCantDispAlquiler(Integer cantidad) { this.cantDispAlquiler = cantidad; }

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

	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }
}
