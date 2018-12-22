package com.retuerta.GamerStore.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.retuerta.GamerStore.entities.Juego;

public interface JuegoRepository extends CrudRepository<Juego, Long> {
	public List<Juego> findAllByOrderByNombreAsc();

}
