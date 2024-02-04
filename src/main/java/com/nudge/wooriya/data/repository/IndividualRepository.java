package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Company;
import com.nudge.wooriya.data.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {
    Optional<Individual> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Individual> findByName(String name);
}