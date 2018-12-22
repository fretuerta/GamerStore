package com.retuerta.GamerStore.DTO;

import com.retuerta.GamerStore.entities.Articulo;
import com.retuerta.GamerStore.entities.Juego;
import com.retuerta.GamerStore.entities.Plataforma;

public class ArticuloDTO extends Articulo {

  	private Juego juego;
	private Plataforma plataforma;
	
	public ArticuloDTO() {super();}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
	
}
