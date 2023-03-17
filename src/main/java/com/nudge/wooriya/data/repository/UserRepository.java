package com.nudge.wooriya.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nudge.wooriya.data.entity.Member;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String username);

    boolean existsByMemberId(String username);
}