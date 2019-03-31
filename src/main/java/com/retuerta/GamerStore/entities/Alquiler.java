package com.retuerta.GamerStore.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alquileres")
public class Alquiler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	private Articulo articulo;
	
	private Date fechaInicio;
	private Date fechaFin;
	

	public Alquiler() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Articulo getArticulo() { return articulo; }
	public void setArticulo(Articulo articulo) { this.articulo = articulo; }
	
	public Date getFechaInicio() { return fechaInicio; }
	public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
	
	public Date getFechaFin() { return fechaFin; }
	public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
	
}