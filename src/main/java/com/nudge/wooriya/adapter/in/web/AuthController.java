package com.nudge.wooriya.adapter.in.web;

import com.nudge.wooriya.application.port.in.Mail.dto.MailConfirmCodeDto;
import com.nudge.wooriya.common.config.security.TokenInfo;
import com.nudge.wooriya.application.port.in.Auth.AuthUsecase;
import com.nudge.wooriya.application.port.in.Mail.MailService;
import com.nudge.wooriya.common.OAuth.OAuth2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.nudge.wooriya.application.port.in.Auth.dto.*;
import com.nudge.wooriya.common.OAuth.dto.*;


@Tag(name="Auth", description = "인증/권한 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthUsecase authService;
    private final OAuth2Service oAuth2Service;
    private final MailService mailService;

    @Operation(summary = "login", description = "[Token X] 로그인")
    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginDto loginDto) {
        TokenInfo tokenInfo = authService.login(loginDto);
        return tokenInfo;
    }

    @Operation(summary = "oAuthLogin", description = "[Token X] OAuth 로그인")
    @GetMapping("/login-oauth/google")
    public TokenInfo oauthLogin(@RequestParam("code") String code) {
        TokenInfo tokenInfo = oAuth2Service.oAuthLogin(code);
        return tokenInfo;
    }

    @Operation(summary = "oAuthJoin", description = "[Token O] OAuth 추가 가입 정보")
    @PostMapping("/join-oauth/google")
    public ResponseEntity<Boolean> oauthJoin(OAuthJoinDto oAuthJoinDto) {
        authService.oAuthJoin(oAuthJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Operation(summary = "companyJoin", description = "[Token X] 기업 회원가입")
    @PostMapping("/join/company")
    public ResponseEntity<Boolean> companyJoin(@RequestBody CompanyJoinDto companyJoinDto) throws Exception {
        Boolean result = authService.companyJoin(companyJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "organizationJoin", description = "[Token X] 단체 회원가입")
    @PostMapping("/join/organization")
    public ResponseEntity<Boolean> organizationJoin(@RequestBody OrganizationJoinDto organizationJoinDto) throws Exception {
        Boolean result = authService.organizationJoin(organizationJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "individualJoin", description = "[Token X] 개인 회원가입")
    @PostMapping("/join/individual")
    public ResponseEntity<Boolean> individualJoin(@RequestBody IndividualJoinDto individualJoinDto) throws Exception {
        Boolean result = authService.individualJoin(individualJoinDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "confirmCode", description = "[Token X] 이메일로 인증번호 전송")
    @PostMapping("/join/confirmcode/send")
    public ResponseEntity<Boolean> confirmCode(@RequestBody MailConfirmCodeDto confirmcodeDto) {
        Boolean result = mailService.sendConfirmCode(confirmcodeDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "verifyConfirmCode", description = "[Token X] 인증번호 확인")
    @PostMapping(value="/join/confirmcode/verify")
    public ResponseEntity<?> verifyConfirmCode(@RequestBody MailConfirmCodeDto confirmCodeDto) throws Exception {
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