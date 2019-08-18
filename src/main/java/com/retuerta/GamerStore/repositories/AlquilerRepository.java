package com.retuerta.GamerStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retuerta.GamerStore.entities.Alquiler;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

	@Query(value = "SELECT * FROM alquileres a WHERE a.cliente_id = ?1", nativeQuery = true)
	public List<Alquiler> findAllClienteId(Long clienteId);
	
}
