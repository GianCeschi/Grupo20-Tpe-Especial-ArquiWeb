package org.example.microviaje.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microviaje.dto.TarifaDTO;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
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

    public Tarifa(TarifaDTO tarifaDTO) {
        this.tipo = tarifaDTO.getTipo();
        this.valor = tarifaDTO.getValor();
        this.fechaVigencia = tarifaDTO.getFechaVigencia();
    }

}