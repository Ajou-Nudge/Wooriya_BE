package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.EmailConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmRepository extends JpaRepository<EmailConfirm, String> {
}