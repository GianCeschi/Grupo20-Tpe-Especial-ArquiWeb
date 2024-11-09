package org.example.microusuario.service;

import org.example.microusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UsuarioServicio")
public class UsuarioServicio {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
}
