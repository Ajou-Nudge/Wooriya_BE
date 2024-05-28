package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Landing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LandingRepository extends MongoRepository<Landing, String> {
}
