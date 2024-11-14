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
        // Crear una nueva instancia de Monopatin y configurar sus atributos manualmente
        Monopatin monopatin = new Monopatin();
        monopatin.setIdMonopatin(monopatinDTO.getId());
        monopatin.setEstado(monopatinDTO.getEstado());
        monopatin.setLongitud(monopatinDTO.getLongitud());
        monopatin.setLatitud(monopatinDTO.getLatitud());
        monopatin.setTiempoDeUso(monopatinDTO.getTiempoDeUso());
        monopatin.setTiempoEnPausa(monopatinDTO.getTiempoEnPausa());
        monopatin.setKmsRecorridos(monopatinDTO.getKmsRecorridos());
        monopatin.setIdParada(monopatinDTO.getIdParada());

        // Guardar el Monopatin en la base de datos
        Monopatin savedMonopatin = monopatinRepository.save(monopatin);

        // Convertir la entidad guardada en un DTO y devolverlo
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
            monopatin.setIdParada(idParada);
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

    // Método para obtener monopatines cercanos basados en la ubicación de las paradas
    public List<MonopatinDTO> obtenerMonopatinesCercanos(double longitud, double latitud, double rango) {
        // Calcular los límites de longitud y latitud basados en el rango
        double minLongitud = longitud - rango;
        double maxLongitud = longitud + rango;
        double minLatitud = latitud - rango;
        double maxLatitud = latitud + rango;

        // Obtener las paradas dentro del rango
        List<Parada> paradasCercanas = paradaRepository.findParadasCercanas(minLongitud, maxLongitud, minLatitud, maxLatitud);

        // Obtener los ids de las paradas cercanas
        List<String> idsParadasCercanas = paradasCercanas.stream()
                .map(Parada::getIdParada)
                .collect(Collectors.toList());

        // Obtener los monopatines que hacen referencia a esas paradas
        List<Monopatin> monopatines = monopatinRepository.findByIdParadaIn(idsParadasCercanas);

        // Convertir la lista de Monopatines a MonopatinDTO
        return monopatines.stream()
                .map(MonopatinDTO::new)
                .collect(Collectors.toList());
    }


    // Método para generar el reporte de uso de monopatines por kilómetros
    public List<MonopatinDTO> generarReportePorKilometros(boolean incluirTiempoDePausa) {
        // Obtener todos los monopatines
        List<Monopatin> monopatines = monopatinRepository.findAll();
        // Mapear los monopatines a DTOs y ordenarlos por kilómetros recorridos
        return monopatines.stream()
                .map(monopatin -> {// Crear el DTO de cada monopatín
                    MonopatinDTO dto = new MonopatinDTO(monopatin);
                    // Si se incluye el tiempo de pausa, ajustar los kilómetros recorridos
                    if (incluirTiempoDePausa) {
                        dto.setKmsRecorridos(dto.getKmsRecorridos() + (dto.getTiempoEnPausa()));  // Asumimos que por cada minuto de espera se suma un kilometro
                    }
                    return dto;
                })
                .sorted(Comparator.comparing(MonopatinDTO::getKmsRecorridos).reversed())  // Ordenar de mayor a menor
                .collect(Collectors.toList());
    }
}

