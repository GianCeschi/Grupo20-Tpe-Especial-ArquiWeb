package org.example.micromonopatin.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "monopatines")
public class Monopatin {

    @Id
    private String idMonopatin;

    private String estado; // Ej. "disponible", "en uso", "en mantenimiento"
    private double longitud;
    private double latitud;
    private int tiempoDeUso;
    private int tiempoEnPausa;
    private int kmsRecorridos;

    @DBRef
    private Parada parada; // Relación con Parada (debe tener una referencia)



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

    // Getters y setters
    public String getIdMonopatin() {
        return idMonopatin;
    }

    public void setIdMonopatin(String idMonopatin) {
        this.idMonopatin = idMonopatin;
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

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }
}
