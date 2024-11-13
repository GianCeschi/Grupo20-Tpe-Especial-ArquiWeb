package org.example.microviaje.dto;

import jakarta.persistence.Column;

import java.sql.Timestamp;

public class ViajeDTO {
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private double kmsRecorridos;
    private double montoTotal;

    public ViajeDTO(Timestamp fechaHoraInicio, Timestamp fechaHoraFin, double kmsRecorridos, double montoTotal){
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.kmsRecorridos = kmsRecorridos;
        this.montoTotal = montoTotal;
    }
}
