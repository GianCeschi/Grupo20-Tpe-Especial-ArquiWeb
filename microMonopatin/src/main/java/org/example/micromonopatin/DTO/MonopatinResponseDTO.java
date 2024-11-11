package org.example.micromonopatin.DTO;

import org.example.micromonopatin.entity.Parada;

public class MonopatinResponseDTO {
    private String idMonopatin;
    private String estado; // Ej. "disponible", "en uso", "en mantenimiento"
    private double longitud;
    private double latitud;
    private int tiempoDeUso;
    private int tiempoEnPausa;
    private int kmsRecorridos;
    private Parada parada;


}
