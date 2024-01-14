package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.ProfileDto;
import com.nudge.wooriya.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile/{email}")
    public ProfileDto login(@PathVariable String email) throws Exception {
        ProfileDto profileDto = userService.profile(email);
        return profileDto;
    }

}