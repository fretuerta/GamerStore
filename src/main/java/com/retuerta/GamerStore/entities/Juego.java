package com.retuerta.GamerStore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "juegos")
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String codigo;
	
	@Lob
	private String caratula;
	
	public Juego() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }

	public String getCaratula() { return caratula; }
	public void setCaratula(String caratula) { this.caratula = caratula; }

}
