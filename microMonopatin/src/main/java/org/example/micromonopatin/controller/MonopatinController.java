package org.example.micromonopatin.controller;

import org.example.micromonopatin.service.MonopatinServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinServicio monopatinServicio;

    public MonopatinController(MonopatinServicio monopatinServicio) {
        this.monopatinServicio = monopatinServicio;
    }

    @GetMapping("/conteoPorEstado")
    public Map<String, Long> obtenerConteoPorEstado() {
        return monopatinServicio.obtenerConteoPorEstado();
    }
}
