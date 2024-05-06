package com.nudge.wooriya.common.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String email;
    private String memberRole;
    private String id;
    private Boolean isOAuthRegister;
}