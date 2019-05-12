package com.retuerta.GamerStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.retuerta.GamerStore.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
}
