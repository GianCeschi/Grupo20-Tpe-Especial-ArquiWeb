package org.example.micromonopatin.service;

import org.example.micromonopatin.entity.Parada;
import org.example.micromonopatin.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ParadaServicio")
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }



    //        ******************* METODOS  PARA RECUPERAR PARADAS *******************

    public List<Parada> getAllParadas() {
        return paradaRepository.findAll();
    }

    public Optional<Parada> getParadaById(String id) {
        return paradaRepository.findById(id);
    }



    //        ******************* METODOS  PARA ABM DE PARADAS *******************


    public Parada saveParada(Parada parada) {
        return paradaRepository.save(parada);
    }

    public void deleteParada(String id) {
        paradaRepository.deleteById(id);
    }


}
