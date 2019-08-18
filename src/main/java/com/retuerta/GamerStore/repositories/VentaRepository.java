package com.retuerta.GamerStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retuerta.GamerStore.entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

	@Query(value = "SELECT * FROM ventas v WHERE v.cliente_id = ?1", nativeQuery = true)
	public List<Venta> findAllClienteId(Long clienteId);
	
}
