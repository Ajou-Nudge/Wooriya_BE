package com.nudge.wooriya.common.config.security;

import com.nudge.wooriya.application.port.in.Auth.dto.UserInfoDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UserInfoDto getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(authentication.getName());
        userInfoDto.setMemberRole(authentication.getAuthorities().stream().toList().get(0).toString().replaceAll("ROLE_", ""));

        return userInfoDto;
    }
}