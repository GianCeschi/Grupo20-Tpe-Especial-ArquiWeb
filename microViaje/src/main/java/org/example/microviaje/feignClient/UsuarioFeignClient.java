package org.example.microviaje.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "microUsuario")
public interface UsuarioFeignClient {

    @PutMapping ("/api/usuarios/cuentapagos/pagarviaje/{id}")
    void pagarViaje(@PathVariable Long id, @RequestParam Double montoViaje);
}
