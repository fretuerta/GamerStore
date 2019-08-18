package com.retuerta.GamerStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retuerta.GamerStore.entities.VentaDetalle;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {

	@Query(value = "SELECT * FROM venta_detalle v WHERE v.articulo_id = ?1", nativeQuery = true)
	public List<VentaDetalle> findAllArticuloId(Long articuloId);
	
}
