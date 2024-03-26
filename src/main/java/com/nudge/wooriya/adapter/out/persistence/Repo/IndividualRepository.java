package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Individual;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualRepository extends MongoRepository <Individual,String>{
    Optional<Individual> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Individual> findByName(String name);
}