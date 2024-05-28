package com.nudge.wooriya.application.service;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Landing;
import com.nudge.wooriya.adapter.out.persistence.Repo.LandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandingService {
    @Autowired
    private LandingRepository landingRepository;

    public Landing saveLanding(Landing landing) {
        return landingRepository.save(landing);
    }
}
