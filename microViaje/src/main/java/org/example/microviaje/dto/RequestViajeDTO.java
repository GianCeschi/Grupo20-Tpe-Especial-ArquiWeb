package org.example.microviaje.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestViajeDTO {
    private Long idUsuario;
    private Long idCuentaPago;
    private String idMonopatin;
    private LocalDate fechaInicio;
    private int tiempoPausa;
    private int tiempoViaje;
    private int kmRecorridos;
    private double montoTotal;
    private String paradaDestino;


    public RequestViajeDTO(Long idUsuario,Long idCuentaPago, String idMonopatin, LocalDate fechaInicio, int tiempoPausa, int tiempoViaje, int kmRecorridos, double montoTotal,String paradaDestino) {
        this.idUsuario = idUsuario;
        this.idCuentaPago = idCuentaPago;
        this.idMonopatin = idMonopatin;
        this.fechaInicio = fechaInicio;
        this.tiempoPausa = tiempoPausa;
        this.tiempoViaje = tiempoViaje;
        this.kmRecorridos = kmRecorridos;
        this.montoTotal = montoTotal;
        this.paradaDestino = paradaDestino;
    }
}
