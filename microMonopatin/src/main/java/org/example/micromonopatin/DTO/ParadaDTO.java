package org.example.micromonopatin.DTO;

import org.example.micromonopatin.entity.Parada;

public class ParadaDTO {

    private String idParada;
    private double longitud;
    private double latitud;
    private int capacidadMaxima;

    // Constructor sin argumentos
    public ParadaDTO() {
    }

    // Constructor con todos los argumentos
    public ParadaDTO(String idParada, double longitud, double latitud, int capacidadMaxima) {
        this.idParada = idParada;
        this.longitud = longitud;
        this.latitud = latitud;
        this.capacidadMaxima = capacidadMaxima;
    }
    public ParadaDTO(Parada parada) {
        this.idParada = parada.getIdParada();
        this.longitud = parada.getLongitud();
        this.latitud = parada.getLatitud();
        this.capacidadMaxima = parada.getCapacidadMaxima();
    }

    // Getters y Setters
    public String getIdParada() {
        return idParada;
    }

    public void setIdParada(String idParada) {
        this.idParada = idParada;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
}
