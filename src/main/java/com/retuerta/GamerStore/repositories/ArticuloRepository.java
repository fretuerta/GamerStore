package com.retuerta.GamerStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.retuerta.GamerStore.entities.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
