package org.example.microusuario.dto;

import lombok.Data;
import org.example.microusuario.entity.CuentaPago;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean activo;
    private List<CuentaPagoDTO> cuentas;

    public UsuarioDTO(String apellido, String nombre, String telefono, Boolean activo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
        this.cuentas = new ArrayList<>();
    }

    public UsuarioDTO(String apellido, String nombre, String telefono, Boolean activo, List<CuentaPagoDTO> cuenta) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
        this.cuentas = cuenta;
    }

}
