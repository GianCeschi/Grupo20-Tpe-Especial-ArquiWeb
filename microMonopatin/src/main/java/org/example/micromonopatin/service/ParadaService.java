package org.example.micromonopatin.service;

import org.example.micromonopatin.entity.Parada;
import org.example.micromonopatin.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ParadaServicio")
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }

    public List<Parada> getAllParadas() {
        return paradaRepository.findAll();
    }

    public Parada getParadaById(String id) {
        return paradaRepository.findById(id).orElse(null);
    }

    public Parada saveParada(Parada parada) {
        return paradaRepository.save(parada);
    }

    public void deleteParada(String id) {
        paradaRepository.deleteById(id);
    }


}
