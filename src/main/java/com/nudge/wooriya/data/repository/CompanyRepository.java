package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Company> findByCompanyName(String userName);
}