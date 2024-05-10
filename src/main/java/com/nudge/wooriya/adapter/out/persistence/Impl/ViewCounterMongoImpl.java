package com.nudge.wooriya.adapter.out.persistence.Impl;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.adapter.out.persistence.ViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ViewCounterMongoImpl implements ViewCountService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ViewCounterMongoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void incrementViewCount(String cafeId){
        mongoTemplate.updateFirst(
                new Query(Criteria.where("cafeId").is(cafeId)),
                new Update().inc("viewCount", 1),
                Cafe.class
        );
    }
}
