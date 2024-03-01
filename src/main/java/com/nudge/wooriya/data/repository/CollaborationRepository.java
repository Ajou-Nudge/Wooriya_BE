package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Collaboration.Collaboration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaborationRepository extends MongoRepository<Collaboration, String> {
    Optional<Collaboration> findByEmail(String email);
}


