package org.example.micromonopatin.controller;

import org.example.micromonopatin.entity.Parada;
import org.example.micromonopatin.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    public ParadaController(ParadaService paradaService){
        this.paradaService = paradaService;
    }



    //        ******************* METODOS  PARA ABM DE PARADAS *******************


    // Crear una parada
    @PostMapping
    public Parada createParada(@RequestBody Parada parada) {
        return paradaService.saveParada(parada);
    }

    // Modificar una parada
    @PutMapping("/{id}")
    public ResponseEntity<Parada> updateParada(@PathVariable String id, @RequestBody Parada parada) {
        return paradaService.getParadaById(id).map(existingParada -> {
            parada.setIdParada(id);
            return ResponseEntity.ok(paradaService.saveParada(parada));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Borrar una parada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParada(@PathVariable String id) {
        if (paradaService.getParadaById(id).isPresent()) {
            paradaService.deleteParada(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    //        ******************* METODOS  PARA RECUPERAR PARADAS *******************

    @GetMapping
    public List<Parada> getAllParadas() {
        return paradaService.getAllParadas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> getParadaById(@PathVariable String id) {
        return paradaService.getParadaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
