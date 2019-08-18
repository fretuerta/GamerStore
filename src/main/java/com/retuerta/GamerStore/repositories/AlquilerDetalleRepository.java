package com.retuerta.GamerStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retuerta.GamerStore.entities.AlquilerDetalle;

public interface AlquilerDetalleRepository extends JpaRepository<AlquilerDetalle, Long> {

	@Query(value = "SELECT * FROM alquiler_detalle a WHERE a.articulo_id = ?1", nativeQuery = true)
	public List<AlquilerDetalle> findAllArticuloId(Long articuloId);

}
