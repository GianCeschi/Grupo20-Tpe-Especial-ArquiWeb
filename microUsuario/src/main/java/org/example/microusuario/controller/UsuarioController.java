package org.example.microusuario.controller;

import org.example.microusuario.dto.RequestUsuarioDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.entity.Usuario;
import org.example.microusuario.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("")
    public ResponseEntity<UsuarioDTO> save(@RequestBody RequestUsuarioDTO request) {
        final var result =  this.usuarioServicio.save(request);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return usuarioServicio.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody RequestUsuarioDTO nuevoUsuario) throws Exception {
        var resultado = usuarioServicio.update(id, nuevoUsuario);
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping ("")
    public Iterable<UsuarioDTO> getAll() {
        return usuarioServicio.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
        return usuarioServicio.getById(id);
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<UsuarioDTO> cambiarEstado(@PathVariable Long id) {
        return this.usuarioServicio.cambiarEstado(id);
    }

}
