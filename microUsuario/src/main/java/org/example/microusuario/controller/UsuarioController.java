package org.example.microusuario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Dar de alta un usuario",
            description = "Este endpoint permite crear un usuario",
            operationId = "saveUsuario",
            tags = {"Usuario", "Crear"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    @PostMapping("")
    public ResponseEntity<UsuarioDTO> save(@RequestBody RequestUsuarioDTO request) {
        final var result =  this.usuarioServicio.save(request);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = "Este endpoint permite eliminar un usuario segun su ID",
            operationId = "deleteUsuario",
            tags = {"Usuario", "Eliminar"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return usuarioServicio.delete(id);
    }


    @Operation(
            summary = "Actualizar un usuario",
            description = "Este endpoint actualiza los datos de un usuario existente especificando su ID.",
            operationId = "updateUser",
            tags = {"Usuario", "Actualizar"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody RequestUsuarioDTO nuevoUsuario) throws Exception {
        var resultado = usuarioServicio.update(id, nuevoUsuario);
        return ResponseEntity.ok().body(resultado);
    }

    @Operation(
            summary = "Obtener usuarios",
            description = "Este endpoint permite obtener usuarios",
            operationId = "getUsuarios",
            tags = {"Usuario", "ObtenerTodos"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @GetMapping ("")
    public Iterable<UsuarioDTO> getAll() {
        return usuarioServicio.getAll();
    }

    @Operation(
            summary = "Obtener usuario",
            description = "Este endpoint permite obtener usuario segun su ID",
            operationId = "getUsuario",
            tags = {"Usuario", "Obtener"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
        return usuarioServicio.getById(id);
    }


    @Operation(
            summary = "Cambiar estado de un usuario",
            description = "Este endpoint permite cambiar el estado de un usuario existente especificando su ID. Cambia entre activo e inactivo.",
            operationId = "patchUsuario",
            tags = {"Usuario", "Patch"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @PatchMapping("/estado/{id}")
    public ResponseEntity<UsuarioDTO> cambiarEstado(@PathVariable Long id) {
        return this.usuarioServicio.cambiarEstado(id);
    }

}
