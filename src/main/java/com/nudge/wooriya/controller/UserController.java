package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.NotificationResponseDto;
import com.nudge.wooriya.data.dto.ProfileDto;
import com.nudge.wooriya.data.dto.ProposalPostResponseDto;
import com.nudge.wooriya.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile/{email}")
    public ResponseEntity<ProfileDto> login(@PathVariable String email) throws Exception {
        ProfileDto profileDto = userService.profile(email);
        return ResponseEntity.status(HttpStatus.OK).body(profileDto);
    }

    @GetMapping("/notification")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotification() throws Exception {
        List<NotificationResponseDto> notificationResponseDtos = userService.getAllNotification();
        return ResponseEntity.status(HttpStatus.OK).body(notificationResponseDtos);
    }

    @PostMapping("/notification/read/{id}")
    public ResponseEntity<Boolean> readNotification(@PathVariable Long notificationId) throws Exception {
        Boolean result = userService.readNotification(notificationId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}