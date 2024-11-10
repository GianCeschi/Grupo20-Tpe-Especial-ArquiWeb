package org.example.micromonopatin.repository;

import org.example.micromonopatin.entity.Parada;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParadaRepository extends MongoRepository<Parada,String> {

}
