package org.example.micromonopatin.repository;

import org.example.micromonopatin.entity.Parada;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ParadaRepository extends MongoRepository<Parada,String> {

    @Query("{ 'longitud': { $gte: ?0, $lte: ?1 }, 'latitud': { $gte: ?2, $lte: ?3 } }")
    List<Parada> findParadasCercanas(double minLongitud, double maxLongitud, double minLatitud, double maxLatitud);
}
