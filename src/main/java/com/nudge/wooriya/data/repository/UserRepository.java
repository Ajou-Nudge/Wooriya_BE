package com.nudge.wooriya.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nudge.wooriya.data.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Member> findByUserName(String userName);
}