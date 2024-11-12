package org.example.micromonopatin.repository;


import org.example.micromonopatin.entity.Monopatin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MonopatinRepository extends MongoRepository<Monopatin, String> {

    long countByEstado(String disponible);

    List<Monopatin> findAll();

    // Método para obtener monopatines que estén asociados a una parada en uso y cercanos a las coordenadas dadas
    @Query("{ 'parada' : { $ne: null }, 'estado' : 'disponible', 'parada.longitud' : { $gte: ?0, $lte: ?1 }, 'parada.latitud' : { $gte: ?2, $lte: ?3 } }")
    List<Monopatin> findMonopatinesCercanos(
            double minLongitud, double maxLongitud, double minLatitud, double maxLatitud);

}
