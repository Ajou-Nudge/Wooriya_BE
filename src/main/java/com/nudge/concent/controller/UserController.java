package com.nudge.concent.controller;

import com.nudge.concent.config.security.TokenInfo;
import com.nudge.concent.data.dto.UserInfoDto;
import com.nudge.concent.data.dto.UserJoinDto;
import com.nudge.concent.data.dto.UserLoginDto;
import com.nudge.concent.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDto userLoginDto) {
        TokenInfo tokenInfo = userService.login(userLoginDto);
        return tokenInfo;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDto userJoinDto) {
        userService.join(userJoinDto);

        return ResponseEntity.status(HttpStatus.OK).body(userJoinDto.getRole());
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> info() {
        UserInfoDto userInfoDto = userService.info();
        return ResponseEntity.status(HttpStatus.OK).body(userInfoDto);
    }
}