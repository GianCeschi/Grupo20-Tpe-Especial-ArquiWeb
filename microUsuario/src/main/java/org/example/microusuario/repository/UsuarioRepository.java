package org.example.microusuario.repository;

import org.example.microusuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> save(Usuario nuevoUsuario);



    Optional<Usuario> findById(Integer id);

    int delete(Integer id);
}
