package org.example.microviaje.service;

import org.example.microviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ViajeServicio")
public class ViajeServicio {
    @Autowired
    private ViajeRepository viajeRepository;

    public ViajeServicio(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }


}
