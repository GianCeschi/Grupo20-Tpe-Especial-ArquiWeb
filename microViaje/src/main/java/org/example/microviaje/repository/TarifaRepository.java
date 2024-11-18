package org.example.microviaje.repository;

import org.example.microviaje.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    // busco en la tabla tarifa el valor de tarifa alta mas reciente, considerando que existan valores a implementar en el futuro
    @Query("SELECT t.valor FROM Tarifa t WHERE t.tipo = 'alta por pausa extensa' AND t.fechaVigencia <= :today " +
            "ORDER BY t.fechaVigencia DESC LIMIT 1")
    Optional<Double> findValorTarifaAltaActual(LocalDate today);
    // busco en la tabla tarifa el valor de tarifa baja mas reciente, considerando que existan valores a implementar en el futuro
    @Query("SELECT t.valor FROM Tarifa t WHERE t.tipo = 'baja' AND t.fechaVigencia <= :today " +
            "ORDER BY t.fechaVigencia DESC LIMIT 1")
    Optional<Double> findValorTarifaBajaActual(LocalDate today);

}
