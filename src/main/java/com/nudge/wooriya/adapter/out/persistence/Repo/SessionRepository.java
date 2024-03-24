package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessionRepository extends MongoRepository<Session, String> {
    Session findByMembersContains(String email1, String email2);
    List<Session> findByMembersContains(String email);
    Boolean existsByMembersContains(String email);
}
