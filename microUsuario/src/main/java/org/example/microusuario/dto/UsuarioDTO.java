package org.example.microusuario.dto;

public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String telefono;

    public UsuarioDTO(String apellido, String nombre, String telefono) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}
