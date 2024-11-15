package org.example.microviaje.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microMonopatin")
public interface MonopatinFeignClient {

    @GetMapping("/api/monopatines/{idMonopatin}/comenzar-viaje")
    void comenzarViaje(@PathVariable("idMonopatin") String idMonopatin);

    @GetMapping("/api/monopatines/{idMonopatin}/finalizar-viaje")
    void finalizarViaje(@PathVariable("idMonopatin") String idMonopatin, @RequestParam String idParadaDestino,
                        @RequestParam int kmRecorridos, @RequestParam int tiempoPausa,
                        @RequestParam int tiempoUso);


}
