package org.example.microviaje.dto;

import java.io.Serializable;


public class ReporteViajeMonopatinDTO implements Serializable  {
    private int idMonopatin;
    private int cantidad;

    public ReporteViajeMonopatinDTO(int idMonopatin, int cantidad) {
        this.idMonopatin = idMonopatin;
        this.cantidad = cantidad;
    }

    public int getIdMonopatin() {
        return idMonopatin;
    }

    public void setIdMonopatin(int idMonopatin) {
        this.idMonopatin = idMonopatin;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
