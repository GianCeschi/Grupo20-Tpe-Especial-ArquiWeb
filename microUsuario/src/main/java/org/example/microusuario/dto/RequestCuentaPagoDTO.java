package org.example.microusuario.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCuentaPagoDTO {
    private String nombre;
    private double saldo;

    public RequestCuentaPagoDTO(String nombre) {
        this.nombre = nombre;
        this.saldo = 0;
    }

    public RequestCuentaPagoDTO(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

}
