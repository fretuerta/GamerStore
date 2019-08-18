package com.retuerta.GamerStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.entities.Plataforma;
import com.retuerta.GamerStore.repositories.PlataformaRepository;

@Service
public class PlataformaService {
	
	@Autowired
	private PlataformaRepository plataformaRepository;
	
	@Autowired
	private ArticuloService articuloService;

	public List<Plataforma> getPlataformas() {
		return plataformaRepository.findAllByOrderByNombreAsc();
	}
	
	public Plataforma getPlataforma(long id) {
		Optional<Plataforma> plataforma = plataformaRepository.findById(id);
		return plataforma.get();
	}
	
	public Plataforma addPlataforma(Plataforma plataforma) {
		plataforma.setId(null);
		Plataforma plataformaResult = plataformaRepository.save(plataforma);
		return plataformaResult;
	}
	
	public Plataforma updatePlataforma(Plataforma plataforma) {
		Plataforma plataformaResult = plataformaRepository.save(plataforma);
		return plataformaResult;
	}
	
	public boolean deletePlataforma(long id) {

		articuloService.deleteArticuloPlataformaId(id);
		
		if (plataformaRepository.existsById(id)) {
			plataformaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
