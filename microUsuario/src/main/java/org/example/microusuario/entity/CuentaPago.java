package org.example.microusuario.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CuentaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCuentaPago;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private LocalDate fechaAlta;

    @Column( nullable = false)
    private double saldo;

}