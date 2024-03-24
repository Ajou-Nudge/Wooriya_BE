package com.nudge.wooriya.common.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    COMPANY("ROLE_COMPANY"),
    ORGANIZATION("ROLE_ORG"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}