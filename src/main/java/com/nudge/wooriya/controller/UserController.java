package com.nudge.wooriya.controller;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.dto.UserJoinDto;
import com.nudge.wooriya.data.dto.UserLoginDto;
import com.nudge.wooriya.service.MailService;
import com.nudge.wooriya.service.UserService;
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
    private final MailService mailService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDto userLoginDto) {
        TokenInfo tokenInfo = userService.login(userLoginDto);
        return tokenInfo;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDto userJoinDto) throws Exception {
        userService.join(userJoinDto);

        return ResponseEntity.status(HttpStatus.OK).body(userJoinDto.getRole());
    }

    @PostMapping("/join/confirmcode")
    public ResponseEntity<String> confirmCode(@RequestBody String mailAddress) {
        String result = mailService.sendConfirmCode(mailAddress);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value="/join/confirm-mail/{confirmCode}")
    public ResponseEntity<?> verifyConfirmCode(@PathVariable String confirmCode) throws Exception {
        String result = mailService.verifyConfirmCode(confirmCode);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> info() {
        UserInfoDto userInfoDto = userService.info();
        return ResponseEntity.status(HttpStatus.OK).body(userInfoDto);
    }
}