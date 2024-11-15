package org.example.microviaje.controller;

import org.example.microviaje.dto.ReporteViajeMonopatinDTO;
import org.example.microviaje.dto.RequestViajeDTO;
import org.example.microviaje.dto.ViajeDTO;
import org.example.microviaje.entity.Viaje;
import org.example.microviaje.service.ViajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    @Autowired
    private ViajeServicio viajeServicio;

    public ViajeController(ViajeServicio viajeServicio) {
        this.viajeServicio = viajeServicio;
    }

    @PostMapping("")
    public ResponseEntity<ViajeDTO> save(@RequestBody RequestViajeDTO request) {
        final var result =  this.viajeServicio.save(request);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViajeDTO> update(@PathVariable Long id, @RequestBody RequestViajeDTO nuevoViaje) throws Exception {
        var resultado = viajeServicio.update(id, nuevoViaje);
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping("")
    public Iterable<Viaje> getAll() {
        return viajeServicio.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> get(@PathVariable Long id) {
        return viajeServicio.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){return viajeServicio.delete(id);}

    @GetMapping("/viajesPorMonopatin/{anio}/{cant}")
    public Iterable<ReporteViajeMonopatinDTO> getViajesPorMonopatin(@PathVariable int anio, @PathVariable int cant) throws Exception{
        return viajeServicio.getViajesPorMonopatin(anio, cant);
    }

    @GetMapping("/facturacion/{mes1}/{anio1}/{mes2}/{anio2}")
    public Double getTotalFacturacion(@PathVariable int mes1, @PathVariable int anio1,
                                      @PathVariable int mes2, @PathVariable int anio2) throws Exception {
        return viajeServicio.getTotalFacturacion(mes1,anio1,mes2,anio2);
    }

}
