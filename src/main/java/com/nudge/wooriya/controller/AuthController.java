package com.nudge.wooriya.controller;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.service.AuthService;
import com.nudge.wooriya.service.MailService;
//import com.nudge.wooriya.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final MailService mailService;
//    private final OAuthService oAuthService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginDto loginDto) {
        TokenInfo tokenInfo = authService.login(loginDto);
        return tokenInfo;
    }

//    @GetMapping("/login/oauth2/code/google")
//    public ResponseEntity<Boolean> getAccessToken(@RequestParam("code") String code) {
//        oAuthService.login(code);
//        return ResponseEntity.status(HttpStatus.OK).body(true);
//    }

    @PostMapping("/join/company")
    public ResponseEntity<Boolean> companyJoin(@RequestBody CompanyJoinDto companyJoinDto) throws Exception {
        authService.companyJoin(companyJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping("/join/organization")
    public ResponseEntity<Boolean> organizationJoin(@RequestBody OrganizationJoinDto organizationJoinDto) throws Exception {
        authService.organizationJoin(organizationJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping("/join/confirmcode/send")
    public ResponseEntity<Boolean> confirmCode(@RequestBody ConfirmCodeDto confirmcodeDto) {
        Boolean result = mailService.sendConfirmCode(confirmcodeDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value="/join/confirmcode/verify")
    public ResponseEntity<?> verifyConfirmCode(@RequestBody ConfirmCodeDto confirmCodeDto) throws Exception {
        Boolean result = mailService.verifyConfirmCode(confirmCodeDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/info")
    public UserInfoDto info() {
        UserInfoDto userInfoDto = authService.info();
        return userInfoDto;
    }
}