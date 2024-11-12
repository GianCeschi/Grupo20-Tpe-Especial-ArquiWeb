package org.example.micromonopatin.service;

import org.example.micromonopatin.DTO.MonopatinDTO;
import org.example.micromonopatin.entity.Monopatin;
import org.example.micromonopatin.entity.Parada;
import org.example.micromonopatin.repository.MonopatinRepository;
import org.example.micromonopatin.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("MonopatinServicio")
public class MonopatinServicio {
    @Autowired
    private MonopatinRepository monopatinRepository;
    @Autowired
    private ParadaRepository paradaRepository;

    public MonopatinServicio(MonopatinRepository monopatinRepository, ParadaRepository paradaRepository) {
        this.monopatinRepository = monopatinRepository;
        this.paradaRepository = paradaRepository;
    }



    //        ******************* METODOS  PARA RECUPERAR MONOPATINES *******************

    public List<MonopatinDTO> getAllMonopatines() {
        // Obtener la lista de Monopatin desde el repositorio y convertir a MonopatinDTO
        return monopatinRepository.findAll()
                .stream()
                .map(MonopatinDTO::new) // Usar el constructor
                .collect(Collectors.toList());
    }

    public Optional<MonopatinDTO> getMonopatinById(String id) {
        return monopatinRepository.findById(id)
                .map(MonopatinDTO::new);
    }



    //        ******************* METODOS  PARA ABM DE MONOPATINES *******************

    public MonopatinDTO saveMonopatin(MonopatinDTO monopatinDTO) {
        // Usar el constructor de Monopatin que recibe MonopatinDTO
        Monopatin monopatin = new Monopatin(monopatinDTO);

        // Guardar Monopatin en la base de datos
        Monopatin savedMonopatin = monopatinRepository.save(monopatin);

        // Usar el constructor de MonopatinDTO para la conversión
        return new MonopatinDTO(savedMonopatin);
    }

    public void deleteMonopatin(String id) {
        monopatinRepository.deleteById(id);
    }



    //        ******************* METODO  PARA ASIGNAR PARADA A MONOPATINES *******************

    public void asignarParada(String idMonopatin, String idParada) {
        Monopatin monopatin = monopatinRepository.findById(idMonopatin).orElse(null);
        Parada parada = paradaRepository.findById(idParada).orElse(null);

        if (monopatin != null && parada != null) {
            monopatin.setParada(parada);
            monopatinRepository.save(monopatin);
        }
    }



    //        ******************* METODOS  PARA MANTENIMIENTO DE MONOPATINES *******************


    public void registrarMantenimiento(String idMonopatin) {
        Monopatin monopatin = monopatinRepository.findById(idMonopatin).orElse(null);
        if (monopatin != null) {
            monopatin.setEstado("en mantenimiento");
            monopatinRepository.save(monopatin);
        }
    }

    public void finalizarMantenimiento(String idMonopatin) {
        Monopatin monopatin = monopatinRepository.findById(idMonopatin).orElse(null);
        if (monopatin != null && monopatin.getEstado().equals("en mantenimiento")) {
            monopatin.setEstado("disponible");
            monopatin.setKmsRecorridos(0);
            monopatin.setTiempoDeUso(0);
            monopatin.setTiempoEnPausa(0);
            monopatinRepository.save(monopatin);
        }
    }



    //        ******************* METODOS  PARA REPORTES SOLICITADOS *******************

    public Map<String, Long> obtenerConteoPorEstado() {
        long enOperacion = monopatinRepository.countByEstado("disponible");
        long enMantenimiento = monopatinRepository.countByEstado("en mantenimiento");

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("enOperacion", enOperacion);
        resultado.put("enMantenimiento", enMantenimiento);

        return resultado;
    }

    public List<MonopatinDTO> reportePorKilometros() {
        return monopatinRepository.findAll().stream()
                .map(MonopatinDTO::new)
                .sorted(Comparator.comparing(MonopatinDTO::getKmsRecorridos).reversed())
                .collect(Collectors.toList());
    }

    public List<MonopatinDTO> reportePorTiempo(boolean considerarTiempoEnPausa) {
        return monopatinRepository.findAll().stream()
                .map(MonopatinDTO::new)
                .sorted(Comparator.comparing(monopatinDTO -> {
                    MonopatinDTO m = (MonopatinDTO) monopatinDTO;  // Tengo que hacer este casting, porque devuelve un objeto y no puedo accedder a los getters de Monopatin
                    if (considerarTiempoEnPausa) {
                        return m.getTiempoDeUso() - m.getTiempoEnPausa();
                    } else {
                        return m.getTiempoDeUso();
                    }
                }).reversed())
                .collect(Collectors.toList());
    }
}
