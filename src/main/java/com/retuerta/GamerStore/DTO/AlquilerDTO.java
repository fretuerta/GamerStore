package com.retuerta.GamerStore.DTO;

import java.util.List;

import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.AlquilerDetalle;

public class AlquilerDTO extends Alquiler {

  	private List<AlquilerDetalle> alquilerDetalles;
	
	public AlquilerDTO() {super();}

	public List<AlquilerDetalle> getAlquilerDetalles() {
		return alquilerDetalles;
	}

	public void setAlquilerDetalles(List<AlquilerDetalle> alquilerDetalles) {
		this.alquilerDetalles = alquilerDetalles;
	}

}
