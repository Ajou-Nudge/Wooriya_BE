package com.nudge.wooriya.controller;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.service.AuthService;
import com.nudge.wooriya.service.MailService;
//import com.nudge.wooriya.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name="Auth", description = "인증/권한 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final MailService mailService;
//    private final OAuthService oAuthService;

    @Operation(summary = "login", description = "[Token X] 로그인")
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

    @Operation(summary = "companyJoin", description = "[Token X] 기업 회원가입")
    @PostMapping("/join/company")
    public ResponseEntity<Boolean> companyJoin(@RequestBody CompanyJoinDto companyJoinDto) throws Exception {
        authService.companyJoin(companyJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Operation(summary = "organizationJoin", description = "[Token X] 단체 회원가입")
    @PostMapping("/join/organization")
    public ResponseEntity<Boolean> organizationJoin(@RequestBody OrganizationJoinDto organizationJoinDto) throws Exception {
        authService.organizationJoin(organizationJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Operation(summary = "confirmCode", description = "[Token X] 이메일로 인증번호 전송")
    @PostMapping("/join/confirmcode/send")
    public ResponseEntity<Boolean> confirmCode(@RequestBody ConfirmCodeDto confirmcodeDto) {
        Boolean result = mailService.sendConfirmCode(confirmcodeDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "verifyConfirmCode", description = "[Token X] 인증번호 확인")
    @PostMapping(value="/join/confirmcode/verify")
    public ResponseEntity<?> verifyConfirmCode(@RequestBody ConfirmCodeDto confirmCodeDto) throws Exception {
        Boolean result = mailService.verifyConfirmCode(confirmCodeDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "info", description = "[Token O] AccessToken으로 유저 정보 확인")
    @GetMapping("/info")
    public UserInfoDto info() {
        UserInfoDto userInfoDto = authService.info();
        return userInfoDto;
    }
}