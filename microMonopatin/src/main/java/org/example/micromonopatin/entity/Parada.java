package org.example.micromonopatin.entity;
import lombok.Data;
import org.example.micromonopatin.DTO.ParadaDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "paradas")
public class Parada {

    @Id
    private String idParada;

    private double longitud;
    private double latitud;
    private int capacidadMaxima;

    public Parada(ParadaDTO paradaDTO) {
        this.idParada = paradaDTO.getIdParada();
        this.longitud = paradaDTO.getLongitud();
        this.latitud = paradaDTO.getLatitud();
        this.capacidadMaxima = paradaDTO.getCapacidadMaxima();
    }

    // Getters y setters
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