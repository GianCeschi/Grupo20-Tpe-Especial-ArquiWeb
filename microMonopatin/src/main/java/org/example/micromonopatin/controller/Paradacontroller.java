package org.example.micromonopatin.controller;

import org.example.micromonopatin.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/parada")
public class Paradacontroller {

    @Autowired
    private ParadaService paradaService;

    public ParadaController(ParadaService paradaService){
        this.paradaService = paradaService;
    }

}
