package org.example.microviaje.controller;

import org.example.microviaje.service.ViajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    @Autowired
    private ViajeServicio viajeServicio;

    public ViajeController(ViajeServicio viajeServicio) {
        this.viajeServicio = viajeServicio;
    }
}
