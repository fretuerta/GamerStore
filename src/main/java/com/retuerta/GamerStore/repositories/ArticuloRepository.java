package com.retuerta.GamerStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retuerta.GamerStore.entities.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

	@Query(value = "SELECT * FROM articulos a WHERE a.plataforma_id = ?1", nativeQuery = true)
	public List<Articulo> findAllPlataformaId(Long plataformaId);

	@Query(value = "SELECT * FROM articulos a WHERE a.juego_id = ?1", nativeQuery = true)
	public List<Articulo> findAllJuegoId(Long juegoId);
	
}
