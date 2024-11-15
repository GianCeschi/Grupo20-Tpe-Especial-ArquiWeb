package org.example.microviaje.controller;

import org.example.microviaje.dto.TarifaDTO;
import org.example.microviaje.entity.Tarifa;
import org.example.microviaje.service.TarifaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/viajes")
public class TarifaController {
    @Autowired
    private TarifaServicio tarifaServicio;

    public TarifaController (TarifaServicio tarifaServicio) {
        this.tarifaServicio = tarifaServicio;
    }

    @GetMapping("/tarifas/{id}")
    public ResponseEntity<TarifaDTO> getById(@PathVariable Long id) {
        return tarifaServicio.getById(id);
    }


    @GetMapping("/tarifas")
    public Iterable<TarifaDTO> getAll(){
        return tarifaServicio.getAll();
    }

    @PostMapping("/tarifas")
    public ResponseEntity<TarifaDTO> save(@RequestBody TarifaDTO request) {
        final var result =  this.tarifaServicio.save(request);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/tarifas/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return tarifaServicio.delete(id);
    }

    @PutMapping("/tarifas/{id}")
    public ResponseEntity<TarifaDTO> update(@PathVariable Long id, @RequestBody TarifaDTO tarifaNueva) throws Exception {
        var resultado = tarifaServicio.update(id, tarifaNueva);
        return ResponseEntity.ok().body(resultado);
    }

    @PutMapping("/tarifas/ajustar/{id}")
    public ResponseEntity<Tarifa> ajustarTarifa(@PathVariable Long id, @RequestParam double nuevoValor,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nuevaFechaVigencia) {

        var resultado = tarifaServicio.ajustarTarifa(id,nuevoValor, nuevaFechaVigencia);
        return ResponseEntity.ok().body(resultado);
    }

}
