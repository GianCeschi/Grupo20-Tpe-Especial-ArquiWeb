package org.example.micromonopatin.repository;


import org.example.micromonopatin.entity.Monopatin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonopatinRepository extends MongoRepository<Monopatin, String> {
    long countByEstado(String disponible);
}
