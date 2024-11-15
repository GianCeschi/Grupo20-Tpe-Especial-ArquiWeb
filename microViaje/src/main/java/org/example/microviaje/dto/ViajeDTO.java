package org.example.microviaje.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class ViajeDTO {
    private LocalDate fechaInicio;
    private LocalTime tiempoPausa;
    private LocalTime tiempoViaje;
    private double kmsRecorridos;
    private double montoTotal;

    public ViajeDTO(LocalDate fechaInicio, LocalTime tiempoPausa,LocalTime tiempoViaje, double kmsRecorridos, double montoTotal){
        this.fechaInicio = fechaInicio;
        this.tiempoPausa = tiempoPausa;
        this.tiempoViaje = tiempoViaje;
        this.kmsRecorridos = kmsRecorridos;
        this.montoTotal = montoTotal;
    }
}
