package org.example.micromonopatin.service;

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

    public List<Monopatin> getAllMonopatines() {
        return monopatinRepository.findAll();
    }

    public Optional<Monopatin> getMonopatinById(String id) {
        return monopatinRepository.findById(id);
    }



    //        ******************* METODOS  PARA ABM DE MONOPATINES *******************

    public Monopatin saveMonopatin(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
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

    public List<Monopatin> reportePorKilometros() {
        return monopatinRepository.findAll().stream()
                .sorted(Comparator.comparing(Monopatin::getKmsRecorridos).reversed())
                .collect(Collectors.toList());
    }

    public List<Monopatin> reportePorTiempo(boolean considerarTiempoEnPausa) {
        return monopatinRepository.findAll().stream()
                .sorted(Comparator.comparing(monopatin -> {
                    Monopatin m = (Monopatin) monopatin;  // Tengo que hacer este casting, porque devuelve un objeto y no puedo accedder a los getters de Monopatin
                    if (considerarTiempoEnPausa) {
                        return m.getTiempoDeUso() - m.getTiempoEnPausa();
                    } else {
                        return m.getTiempoDeUso();
                    }
                }).reversed())
                .collect(Collectors.toList());
    }
}
