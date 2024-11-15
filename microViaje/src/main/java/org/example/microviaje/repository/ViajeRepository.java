package org.example.microviaje.repository;

import feign.Param;
import org.example.microviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ViajeRepository extends JpaRepository <Viaje, Long> {


    //consultar los monopatines con más de X viajes en un cierto año
    @Query(value = "SELECT v.id_monopatin, COUNT(v.id_monopatin) as usos FROM viaje v WHERE YEAR(v.fecha_hora_inicio) = :anio GROUP BY v.id_monopatin HAVING COUNT(v.id_monopatin) > :cant ORDER BY usos DESC", nativeQuery = true)
    public List<Object[]> getCantViajesPorAnioPorMonopatin(@Param("anio") int anio, @Param("cant")int cant);

    @Query("SELECT COALESCE(SUM(v.montoTotal), 0.0) FROM Viaje v " +
            "WHERE v.fechaViaje >=:fechaInicio AND v.fechaViaje <=:fechaFin")
    Double getTotalFacturacion(@Param("inicio") LocalDate fechaInicio, @Param("fin") LocalDate fechaFin);

}
