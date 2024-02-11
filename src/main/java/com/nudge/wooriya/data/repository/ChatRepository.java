package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findBySessionId(String sessionId);
}
