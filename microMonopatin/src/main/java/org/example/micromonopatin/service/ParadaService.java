package org.example.micromonopatin.service;

import org.example.micromonopatin.DTO.ParadaDTO;
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

    public List<ParadaDTO> getAllParadas() {
        return paradaRepository.findAll();
    }

    public Optional<ParadaDTO> getParadaById(String id) {
        return paradaRepository.findById(id);
    }



    //        ******************* METODOS  PARA ABM DE PARADAS *******************


    public ParadaDTO saveParada(ParadaDTO paradaDTO) {
        return paradaRepository.save(paradaDTO);
    }

    public void deleteParada(String id) {
        paradaRepository.deleteById(id);
    }


}
