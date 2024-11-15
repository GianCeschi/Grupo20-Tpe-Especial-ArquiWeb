package org.example.microusuario.controller;

import org.example.microusuario.dto.CuentaPagoDTO;
import org.example.microusuario.dto.RequestCuentaPagoDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.service.CuentaPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class CuentaPagoController {

    @Autowired
    private CuentaPagoServicio cuentaPagoService;

    public CuentaPagoController(CuentaPagoServicio cuentaPagoService) {
        this.cuentaPagoService = cuentaPagoService;
    }

    @GetMapping("/cuentapagos")
    public Iterable<CuentaPagoDTO> getAll() {
        return cuentaPagoService.getAll();
    }

    @GetMapping("/cuentapagos/{id}")
    public ResponseEntity<CuentaPagoDTO> getById(@PathVariable Long id) {
        return cuentaPagoService.getById(id);
    }

    @PostMapping("/cuentapagos/{idUsuario}")
    public ResponseEntity<UsuarioDTO> save(@RequestBody RequestCuentaPagoDTO request, @PathVariable Long idUsuario) {
        final var result =  this.cuentaPagoService.save(request, idUsuario);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/cuentapagos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return cuentaPagoService.delete(id);
    }

    @PutMapping("/cuentapagos/{id}")
    public ResponseEntity<CuentaPagoDTO> update(@PathVariable Long id, @RequestBody RequestCuentaPagoDTO request) throws Exception {
        var resultado =  cuentaPagoService.update(id,request);
        return ResponseEntity.ok().body(resultado);
    }
}