package com.retuerta.GamerStore.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	private Cliente cliente;
	
	private Date fechaVenta;
	private double total;

	public Venta() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }
	
	public Date getFechaVenta() { return fechaVenta; }
	public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
	
	public double getTotal() {return total;}
	public void setTotal(double total) { this.total = total;}
	
}