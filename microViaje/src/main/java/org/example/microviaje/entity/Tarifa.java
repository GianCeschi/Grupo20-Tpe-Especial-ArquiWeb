package org.example.microviaje.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTarifa;

    @Column
    private String tipo; // Ej. "normal", "alta por pausa extensa"

    @Column
    private double valor;

    @Column
    private LocalDate fechaVigencia;

}