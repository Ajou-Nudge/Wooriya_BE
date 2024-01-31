package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Chat;
import com.nudge.wooriya.data.entity.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessionRepository extends MongoRepository<Session, Long> {
    Session findByMembersContains(String email1, String email2);
    List<Session> findByMembersContains(String email);
    Boolean existsByMembersContains(String email);
}
