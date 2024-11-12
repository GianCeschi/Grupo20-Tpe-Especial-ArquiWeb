package org.example.micromonopatin.service;

import org.example.micromonopatin.DTO.MonopatinDTO;
import org.example.micromonopatin.DTO.ParadaDTO;
import org.example.micromonopatin.entity.Monopatin;
import org.example.micromonopatin.entity.Parada;
import org.example.micromonopatin.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ParadaServicio")
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }



    //        ******************* METODOS  PARA RECUPERAR PARADAS *******************

    public List<ParadaDTO> getAllParadas() {
        // Obtener la lista de Monopatin desde el repositorio y convertir a MonopatinDTO
        return paradaRepository.findAll()
                .stream()
                .map(ParadaDTO::new) // Usar el constructor
                .collect(Collectors.toList());
    }

    public Optional<ParadaDTO> getParadaById(String id) {
        return paradaRepository.findById(id)
                .map(ParadaDTO::new);
    }



    //        ******************* METODOS  PARA ABM DE PARADAS *******************


    public ParadaDTO saveParada(ParadaDTO paradaDTO) {
        // Usar el constructor de Parada que recibe ParadaDTO
        Parada parada = new Parada(paradaDTO);

        // Guardar Parada en la base de datos
        Parada savedParada = paradaRepository.save(parada);

        // Usar el constructor de ParadaDTO para la conversión
        return new ParadaDTO(savedParada);
    }


    public void deleteParada(String id) {
        paradaRepository.deleteById(id);
    }


}
