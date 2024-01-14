package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Company;
import com.nudge.wooriya.data.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Optional<Organization> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Organization> findByOrganizationName(String organizationName);
}