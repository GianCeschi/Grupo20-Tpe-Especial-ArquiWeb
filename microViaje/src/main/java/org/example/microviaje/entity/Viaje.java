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
    private String idMonopatin; // FK referenciando al microservicio de Monopatines

    @Column(nullable = false)
    private LocalDate fechaViaje;

    @Column
    private int tiempoPausa;

    @Column
    private int tiempoViaje;

    @Column
    private int kmRecorridos;

    @Column
    private double montoTotal;

    @Column
    private String paradaDestino;

    public Viaje(RequestViajeDTO request) {
        this.idUsuario = request.getIdUsuario();
        this.idCuentaPago = request.getIdCuentaPago();
        this.idMonopatin = request.getIdMonopatin();
        this.fechaViaje = request.getFechaInicio();
        this.tiempoPausa = request.getTiempoPausa();
        this.tiempoViaje = request.getTiempoViaje();
        this.kmRecorridos = request.getKmRecorridos();
        this.montoTotal = request.getMontoTotal();
        this.paradaDestino = request.getParadaDestino();
    }

    public Viaje() {

    }
}