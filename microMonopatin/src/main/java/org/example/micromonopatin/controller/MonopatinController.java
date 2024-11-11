package org.example.micromonopatin.controller;

import org.example.micromonopatin.entity.Monopatin;
import org.example.micromonopatin.service.MonopatinServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Monopatin createMonopatin(@RequestBody Monopatin monopatin) {
        return monopatinServicio.saveMonopatin(monopatin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monopatin> updateMonopatin(@PathVariable String id, @RequestBody Monopatin monopatin) {
        return monopatinServicio.getMonopatinById(id).map(existingMonopatin -> {
            monopatin.setIdMonopatin(id);
            return ResponseEntity.ok(monopatinServicio.saveMonopatin(monopatin));
        }).orElse(ResponseEntity.notFound().build());
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
    public List<Monopatin> getAllMonopatines() {
        return monopatinServicio.getAllMonopatines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monopatin> getMonopatinById(@PathVariable String id) {
        return monopatinServicio.getMonopatinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/conteoPorEstado")
    public Map<String, Long> obtenerConteoPorEstado() {
        return monopatinServicio.obtenerConteoPorEstado();
    }
}
