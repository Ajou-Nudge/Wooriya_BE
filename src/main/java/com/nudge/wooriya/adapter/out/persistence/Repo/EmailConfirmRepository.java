package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.EmailConfirm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfirmRepository extends MongoRepository<EmailConfirm, String> {
}