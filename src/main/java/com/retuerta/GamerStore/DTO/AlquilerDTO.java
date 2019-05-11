package com.retuerta.GamerStore.DTO;

import java.util.List;

import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.AlquilerDetalle;

public class AlquilerDTO extends Alquiler {

  	private List<AlquilerDetalle> alquilerDetalles;
	
	public AlquilerDTO() {super();}
	public AlquilerDTO(Alquiler alquiler) {
		super();
		this.setId(alquiler.getId());
		this.setCliente(alquiler.getCliente());
		this.setFechaInicio(alquiler.getFechaInicio());
		this.setFechaFin(alquiler.getFechaFin());
		this.setTotal(alquiler.getTotal());
	}

	public List<AlquilerDetalle> getAlquilerDetalles() {
		return alquilerDetalles;
	}

	public void setAlquilerDetalles(List<AlquilerDetalle> alquilerDetalles) {
		this.alquilerDetalles = alquilerDetalles;
	}
	
	public void addAlquilerDetalle(AlquilerDetalle alquilerDetalle) {
		this.alquilerDetalles.add(alquilerDetalle);
	}

}
