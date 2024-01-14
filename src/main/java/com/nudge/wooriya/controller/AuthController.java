package com.nudge.wooriya.controller;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.service.AuthService;
import com.nudge.wooriya.service.MailService;
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

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginDto loginDto) {
        TokenInfo tokenInfo = authService.login(loginDto);
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

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

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
}