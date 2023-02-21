package com.nudge.concent.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nudge.concent.data.entity.Member;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String username);

    boolean existsByMemberId(String username);
}