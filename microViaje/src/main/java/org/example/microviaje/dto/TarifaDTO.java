package org.example.microviaje.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TarifaDTO {
    private String tipo;
    private double valor;
    private LocalDate fechaVigencia;

    public TarifaDTO(String tipo, double valor, LocalDate fechaVigencia) {
        this.tipo = tipo;
        this.valor = valor;
        this.fechaVigencia = fechaVigencia;
    }

    @Override
    public String toString() {
        return "TarifaDTO{" +
                "tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", fechaVigencia=" + fechaVigencia +
                '}';
    }
}
