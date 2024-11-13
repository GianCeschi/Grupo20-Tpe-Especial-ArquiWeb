package org.example.microviaje.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestViajeDTO {
    private Long idUsuario;
    private Long idCuentaPago;
    private Long idMonopatin;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private double kmRecorridos;
    private double montoTotal;


    public RequestViajeDTO(Long idUsuario,Long idCuentaPago, Long idMonopatin, Timestamp fechaHoraInicio, Timestamp fechaHoraFin, double kmRecorridos, double montoTotal) {
        this.idUsuario = idUsuario;
        this.idCuentaPago = idCuentaPago;
        this.idMonopatin = idMonopatin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.kmRecorridos = kmRecorridos;
        this.montoTotal = montoTotal;
    }
}
