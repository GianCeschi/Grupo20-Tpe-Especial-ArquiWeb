package org.example.microusuario.repository;

import org.example.microusuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



    int delete(Long id);
}
