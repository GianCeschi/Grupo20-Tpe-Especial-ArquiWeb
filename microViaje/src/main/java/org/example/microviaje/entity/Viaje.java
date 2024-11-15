package org.example.microviaje.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.microviaje.dto.RequestViajeDTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idViaje;

    @Column(nullable = false)
    private Long idUsuario; // FK referenciando al microservicio de Usuarios

    @Column(nullable = false)
    private Long idCuentaPago; // FK referenciando al microservicio de Usuarios

    @Column(nullable = false)
    private Long idMonopatin; // FK referenciando al microservicio de Monopatines

    @Column(nullable = false)
    private LocalDate fechaViaje;

    @Column
    private LocalTime tiempoPausa;

    @Column
    private LocalTime tiempoViaje;

    @Column
    private double kmRecorridos;

    @Column
    private double montoTotal;

    public Viaje(RequestViajeDTO request) {
        this.idUsuario = request.getIdUsuario();
        this.idCuentaPago = request.getIdCuentaPago();
        this.idMonopatin = request.getIdMonopatin();
        this.fechaViaje = request.getFechaInicio();
        this.tiempoPausa = request.getTiempoPausa();
        this.tiempoViaje = request.getTiempoViaje();
        this.kmRecorridos = request.getKmRecorridos();
        this.montoTotal = request.getMontoTotal();
    }

    public Viaje() {

    }
}