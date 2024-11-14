package org.example.micromonopatin.DTO;

import org.example.micromonopatin.entity.Monopatin;

public class MonopatinDTO {

    private String id;
    private String estado; // Ej. "disponible", "en uso", "en mantenimiento"
    private double longitud;
    private double latitud;
    private int tiempoDeUso;
    private int tiempoEnPausa;
    private int kmsRecorridos;
    private String idParada;

    // Constructor sin argumentos
    public MonopatinDTO() {
    }

    // Constructor con todos los argumentos
    public MonopatinDTO(String id, String estado, double longitud, double latitud, int tiempoDeUso, int tiempoEnPausa, int kmsRecorridos, String idParada) {
        this.id = id;
        this.estado = estado;
        this.longitud = longitud;
        this.latitud = latitud;
        this.tiempoDeUso = tiempoDeUso;
        this.tiempoEnPausa = tiempoEnPausa;
        this.kmsRecorridos = kmsRecorridos;
        this.idParada = idParada;
    }

    public MonopatinDTO(Monopatin monopatin) {
        this.id = monopatin.getIdMonopatin();
        this.estado = monopatin.getEstado();
        this.longitud = monopatin.getLongitud();
        this.latitud = monopatin.getLatitud();
        this.tiempoDeUso = monopatin.getTiempoDeUso();
        this.tiempoEnPausa = monopatin.getTiempoEnPausa();
        this.kmsRecorridos = monopatin.getKmsRecorridos();
        this.idParada = monopatin.getIdParada();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public int getTiempoDeUso() {
        return tiempoDeUso;
    }

    public void setTiempoDeUso(int tiempoDeUso) {
        this.tiempoDeUso = tiempoDeUso;
    }

    public int getTiempoEnPausa() {
        return tiempoEnPausa;
    }

    public void setTiempoEnPausa(int tiempoEnPausa) {
        this.tiempoEnPausa = tiempoEnPausa;
    }

    public int getKmsRecorridos() {
        return kmsRecorridos;
    }

    public void setKmsRecorridos(int kmsRecorridos) {
        this.kmsRecorridos = kmsRecorridos;
    }

    public String getIdParada() {
        return idParada;
    }

    public void setIdParada(String idParada) {
        this.idParada = idParada;
    }
}