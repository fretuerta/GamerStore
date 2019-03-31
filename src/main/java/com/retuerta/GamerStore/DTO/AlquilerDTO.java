package com.retuerta.GamerStore.DTO;

import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.Articulo;

public class AlquilerDTO extends Alquiler {

  	private Articulo articulo;
	
	public AlquilerDTO() {super();}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}
