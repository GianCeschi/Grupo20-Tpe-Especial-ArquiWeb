package org.example.microviaje.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

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
    private Timestamp fechaHoraInicio;

    @Column
    private Timestamp fechaHoraFin;

    @Column
    private double kmRecorridos;

    @Column
    private double montoTotal;
}