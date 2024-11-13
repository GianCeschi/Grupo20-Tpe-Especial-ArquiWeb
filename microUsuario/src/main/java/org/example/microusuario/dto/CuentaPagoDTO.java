package org.example.microusuario.dto;

import lombok.Data;

@Data
public class CuentaPagoDTO {

    private String nombre;
    private double saldo;

    public CuentaPagoDTO(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }
}
