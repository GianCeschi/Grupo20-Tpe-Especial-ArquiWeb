package org.example.microusuario.controller;


import org.example.microusuario.dto.RequestCuentaPagoDTO;
import org.example.microusuario.dto.UsuarioDTO;
import org.example.microusuario.service.CuentaPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentapagos")
public class CuentaPagoController {
    @Autowired
    private CuentaPagoServicio cuentaPagoServicio;

    public CuentaPagoController(CuentaPagoServicio cuentaPagoServicio){
        this.cuentaPagoServicio = cuentaPagoServicio;
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> save(@PathVariable Long idUsuario, @RequestBody RequestCuentaPagoDTO request) {
        final var result =  this.cuentaPagoServicio.save(idUsuario,request);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return cuentaPagoServicio.delete(id);
    }
}
