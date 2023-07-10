package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.ConfirmCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmCodeRepository extends JpaRepository<ConfirmCode, Long> {
    ConfirmCode findByConfirmCode(String confirmCode);
}