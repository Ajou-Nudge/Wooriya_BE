package com.nudge.wooriya.controller;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.dto.UserJoinDto;
import com.nudge.wooriya.data.dto.UserLoginDto;
import com.nudge.wooriya.service.MailService;
import com.nudge.wooriya.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDto userLoginDto) {
        TokenInfo tokenInfo = userService.login(userLoginDto);
        return tokenInfo;
    }

    @GetMapping("/login/oauth2")
    public ResponseEntity<Object> oAuth2Login() throws URISyntaxException {
        String uri = "https://kauth.kakao.com/oauth/authorize?client_id="+clientId+"&redirect_uri="+redirectUri+"&response_type=code";
        URI redirectUri = new URI(uri);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/login/oauth2/code/kakao")
        public ResponseEntity<Object> getAccessToken(@RequestParam("code") String code) {
            System.out.println("code = " + code);

            // 1. header 생성
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

            // 2. body 생성
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", clientId);
            params.add("redirect_uri", redirectUri);
            params.add("code", code);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> response = restTemplate.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    Object.class
            );

            System.out.println("response = " + response);

            return response;
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