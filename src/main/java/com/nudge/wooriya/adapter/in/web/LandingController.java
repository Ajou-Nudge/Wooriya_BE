package com.nudge.wooriya.adapter.in.web;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Landing;
import com.nudge.wooriya.application.service.LandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/landing")
public class LandingController {
    @Autowired
    private LandingService landingService;

    @PostMapping("/create")
    public ResponseEntity<Landing> createLanding(@RequestBody Landing landing) {
        Landing savedLanding = landingService.saveLanding(landing);
        return ResponseEntity.ok(savedLanding);
    }
}
