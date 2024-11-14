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
    private List<CuentaPagoDTO> cuentas;

    public UsuarioDTO(String apellido, String nombre, String telefono) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cuentas = new ArrayList<>();
    }

    public UsuarioDTO(String apellido, String nombre, String telefono,List<CuentaPagoDTO> cuenta) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cuentas = cuenta;
    }

}
