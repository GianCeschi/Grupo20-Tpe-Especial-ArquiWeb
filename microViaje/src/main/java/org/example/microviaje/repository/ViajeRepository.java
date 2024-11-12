package org.example.microviaje.repository;

import org.example.microviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository <Viaje, Long> {
}
