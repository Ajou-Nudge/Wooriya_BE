package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, Long> {
    List<Chat> findBySessionId(Long sessionId);
}
