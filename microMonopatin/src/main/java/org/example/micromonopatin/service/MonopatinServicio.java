package org.example.micromonopatin.service;

import org.example.micromonopatin.entity.Monopatin;
import org.example.micromonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("MonopatinServicio")
public class MonopatinServicio {
    @Autowired
    private MonopatinRepository monopatinRepository;

    public MonopatinServicio(MonopatinRepository monopatinRepository) {
        this.monopatinRepository = monopatinRepository;
    }

    public List<Monopatin> getAllMonopatines() {
        return monopatinRepository.findAll();
    }

    public Monopatin getMonopatinById(String id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public Monopatin saveMonopatin(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public void deleteMonopatin(String id) {
        monopatinRepository.deleteById(id);
    }

    public Map<String, Long> obtenerConteoPorEstado() {
        long enOperacion = monopatinRepository.countByEstado("disponible");
        long enMantenimiento = monopatinRepository.countByEstado("en mantenimiento");

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("enOperacion", enOperacion);
        resultado.put("enMantenimiento", enMantenimiento);

        return resultado;
    }
}
