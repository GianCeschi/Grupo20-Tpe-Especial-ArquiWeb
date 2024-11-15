package org.example.micromonopatin.controller;

import org.example.micromonopatin.DTO.MonopatinDTO;
import org.example.micromonopatin.entity.Monopatin;
import org.example.micromonopatin.service.MonopatinServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinServicio monopatinServicio;

    public MonopatinController(MonopatinServicio monopatinServicio) {
        this.monopatinServicio = monopatinServicio;
    }



    //        ******************* METODOS  PARA ABM DE MONOPATINES *******************


    @PostMapping("")
    public MonopatinDTO createMonopatin(@RequestBody MonopatinDTO monopatinDTO) {
        return monopatinServicio.saveMonopatin(monopatinDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonopatin(@PathVariable String id) {
        if (monopatinServicio.getMonopatinById(id).isPresent()) {
            monopatinServicio.deleteMonopatin(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    //        ******************* METODOS  PARA RECUPERAR MONOPATINES *******************


    @GetMapping("")
    public List<MonopatinDTO> getAllMonopatines() {
        return monopatinServicio.getAllMonopatines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonopatinDTO> getMonopatinById(@PathVariable String id) {
        return monopatinServicio.getMonopatinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    //        ******************* METODO PARA ASIGNARLE UNA PARADA A UN MONOPATIN *******************
    @PutMapping("/{idMonopatin}/asignarParada/{idParada}")
    public ResponseEntity<Void> asignarParada(@PathVariable String idMonopatin, @PathVariable String idParada) {
        try {
            monopatinServicio.asignarParada(idMonopatin, idParada);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //        ******************* METODOS  PARA MANTENIMIENTO DE MONOPATINES *******************

    @PutMapping("/{idMonopatin}/registrarMantenimiento")
    public ResponseEntity<Void> registrarMantenimiento(@PathVariable String idMonopatin) {
        try {
            monopatinServicio.registrarMantenimiento(idMonopatin);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{idMonopatin}/finalizarMantenimiento")
    public ResponseEntity<Void> finalizarMantenimiento(@PathVariable String idMonopatin) {
        try {
            monopatinServicio.finalizarMantenimiento(idMonopatin);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //        ******************* METODOS  PARA REPORTES SOLICITADOS *******************

    @GetMapping("/conteoPorEstado")
    public Map<String, Long> obtenerConteoPorEstado() {
        return monopatinServicio.obtenerConteoPorEstado();
    }

    @GetMapping("/reportePorKilometros")
    public ResponseEntity<List<MonopatinDTO>> reportePorKilometros() {
        try {
            List<MonopatinDTO> reporte = monopatinServicio.reportePorKilometros();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reportePorTiempo")
    public ResponseEntity<List<MonopatinDTO>> reportePorTiempo(@RequestParam boolean considerarTiempoEnPausa) {
        try {
            List<MonopatinDTO> reporte = monopatinServicio.reportePorTiempo(considerarTiempoEnPausa);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // Endpoint para obtener los monopatines cercanos
    @GetMapping("/cercanos")
    public ResponseEntity<List<MonopatinDTO>> obtenerMonopatinesCercanos(
            @RequestParam double longitud,
            @RequestParam double latitud,
            @RequestParam double rango) {

        try {
            List<MonopatinDTO> monopatinesCercanos = monopatinServicio.obtenerMonopatinesCercanos(longitud, latitud, rango);
            return ResponseEntity.ok(monopatinesCercanos);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reporteKilometros")
    public ResponseEntity<List<MonopatinDTO>> generarReportePorKilometros(@RequestParam boolean incluirTiempoDePausa) {
        try {
            // Llamar al servicio para generar el reporte
            List<MonopatinDTO> reporte = monopatinServicio.generarReportePorKilometros(incluirTiempoDePausa);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PatchMapping("/{idMonopatin}/comenzar-viaje")
    public ResponseEntity<Void> comenzarViaje(@PathVariable String idMonopatin) {
        try {
            monopatinServicio.comenzarViaje(idMonopatin);
            return ResponseEntity.noContent().build(); // Responde con 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Responde con 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Responde con 500 Internal Server Error
        }
    }

    @PatchMapping("/{idMonopatin}/finalizar-viaje")
    public ResponseEntity<Void> finalizarViaje(
            @PathVariable String idMonopatin,
            @RequestParam String idParadaDestino,
            @RequestParam int kmRecorridos,
            @RequestParam int tiempoPausa,
            @RequestParam int tiempoUso) {
        try {
            // Llamar al servicio con los parámetros
            monopatinServicio.finalizarViaje(
                    idMonopatin,
                    idParadaDestino,
                    kmRecorridos,
                    tiempoPausa,
                    tiempoUso
            );
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }
}




