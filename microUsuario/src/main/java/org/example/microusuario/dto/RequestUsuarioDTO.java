package org.example.microusuario.dto;


import lombok.Data;

@Data
public class RequestUsuarioDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;
    private String password;

    public RequestUsuarioDTO(String nombre, String apellido, String email, String telefono, String rol, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.password = password;
    }

}
