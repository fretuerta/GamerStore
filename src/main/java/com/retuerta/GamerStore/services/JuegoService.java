package com.retuerta.GamerStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.entities.Juego;
import com.retuerta.GamerStore.repositories.JuegoRepository;

@Service
public class JuegoService {

	@Autowired
	private JuegoRepository juegoRepository;
	
	public List<Juego> getJuegos() {
		return juegoRepository.findAllByOrderByNombreAsc();
	}
	
	public Juego getJuego(Long id) {
		Optional<Juego> juego = juegoRepository.findById(id);
		return juego.get();
	}
	
	public Juego addJuego(Juego juego) {
		juego.setId(null);
		return juegoRepository.save(juego);
	}
	
	public Juego updateJuego(Juego juego) {
		return juegoRepository.save(juego);
	}
	
	public boolean deleteJuego(Long id) {

		if (juegoRepository.existsById(id)) {
			juegoRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
