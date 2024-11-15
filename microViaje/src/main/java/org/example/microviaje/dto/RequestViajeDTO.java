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
    private Long idMonopatin;
    private LocalDate fechaInicio;
    private LocalTime tiempoPausa;
    private LocalTime tiempoViaje;
    private double kmRecorridos;
    private double montoTotal;


    public RequestViajeDTO(Long idUsuario,Long idCuentaPago, Long idMonopatin, LocalDate fechaInicio, LocalTime tiempoPausa, LocalTime tiempoViaje, double kmRecorridos, double montoTotal) {
        this.idUsuario = idUsuario;
        this.idCuentaPago = idCuentaPago;
        this.idMonopatin = idMonopatin;
        this.fechaInicio = fechaInicio;
        this.tiempoPausa = tiempoPausa;
        this.tiempoViaje = tiempoViaje;
        this.kmRecorridos = kmRecorridos;
        this.montoTotal = montoTotal;
    }
}
