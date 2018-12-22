package com.retuerta.GamerStore.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.retuerta.GamerStore.entities.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
	public List<Plataforma> findAllByOrderByNombreAsc();
	
	// EJEMPLOS
	@Query(value = "SELECT * FROM plataforma p WHERE p.nombre LIKE 'PlayStation%' ORDER BY nombre", nativeQuery = true)
	public List<Plataforma> findAllPlayStations();
	
	@Query(value = "SELECT * FROM plataforma p WHERE p.nombre LIKE ?1% ORDER BY nombre", nativeQuery = true)
	public List<Plataforma> findAllPlataformasNombreContiene(String nombreParcial);
	
	@Modifying
	@Query(value = "UPDATE plataforma p SET p.nombre = '?2' WHERE p.id = ?1;", nativeQuery = true)
	public int updatePlataformaSetNombreNative(Long id, String nombre);
}
