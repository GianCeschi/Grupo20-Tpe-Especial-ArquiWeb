package org.example.micromonopatin.service;

import org.example.micromonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MonopatinServicio")
public class MonopatinServicio {
    @Autowired
    private MonopatinRepository monopatinRepository;

    public MonopatinServicio(MonopatinRepository monopatinRepository) {
        this.monopatinRepository = monopatinRepository;
    }
}
