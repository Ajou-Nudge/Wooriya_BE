package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.data.entity.Organization;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class OAuthAttributesDto {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public static OAuthAttributesDto of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//        if ("kakao".equals(registrationId)) {
//        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributesDto ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        OAuthAttributesDto oAuthAttributesDto = new OAuthAttributesDto();
        oAuthAttributesDto.setName((String) attributes.get("name"));
        oAuthAttributesDto.setEmail((String) attributes.get("email"));
        oAuthAttributesDto.setAttributes(attributes);
        oAuthAttributesDto.setPicture((String) attributes.get("profile_image"));
        oAuthAttributesDto.setNameAttributeKey(userNameAttributeName);

        return oAuthAttributesDto;
    }

    public Organization toEntity() {
        return Organization.builder()
                .organizationName(name)
                .email(email)
                .build();
    }
}
