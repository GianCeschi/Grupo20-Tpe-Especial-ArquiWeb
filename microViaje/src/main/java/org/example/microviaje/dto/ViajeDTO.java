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
    private int tiempoPausa;
    private int tiempoViaje;
    private int kmsRecorridos;
    private double montoTotal;
    private String idParada;

    public ViajeDTO(LocalDate fechaInicio, int tiempoPausa,int tiempoViaje, int kmsRecorridos, double montoTotal, String idParada) {
        this.fechaInicio = fechaInicio;
        this.tiempoPausa = tiempoPausa;
        this.tiempoViaje = tiempoViaje;
        this.kmsRecorridos = kmsRecorridos;
        this.montoTotal = montoTotal;
        this.idParada = idParada;
    }
}
