package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
    Optional<Organization> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Organization> findByOrganizationName(String organizationName);

    Optional<Organization> findByEmailAndProvider(String email, String provider);
}