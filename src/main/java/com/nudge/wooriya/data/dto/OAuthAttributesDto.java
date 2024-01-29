package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.data.entity.Organization;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

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

    public OAuthAttributesDto parse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        OAuthAttributesDto oAuthAttributesDto = new OAuthAttributesDto();
        oAuthAttributesDto.setEmail(jsonObject.getString("email"));
        oAuthAttributesDto.setName(jsonObject.getString("name"));
        oAuthAttributesDto.setPicture(jsonObject.getString("picture"));

        return oAuthAttributesDto;
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
}
