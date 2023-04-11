package com.nudge.wooriya.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    COMPANY("ROLE_COMPANY"),
    GROUP("ROLE_GROUP"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}