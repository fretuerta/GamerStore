package com.retuerta.GamerStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.retuerta.GamerStore.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
