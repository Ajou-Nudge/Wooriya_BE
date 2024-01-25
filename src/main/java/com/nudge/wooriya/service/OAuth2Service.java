package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.OAuthAttributesDto;
import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.data.entity.SessionUser;
import com.nudge.wooriya.data.repository.OrganizationRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OrganizationRepository organizationRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributesDto attributes = OAuthAttributesDto.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Organization organization = organizationRepository.findByEmail(attributes.getEmail()).get();
        organization.setOrganizationName(attributes.getName());
        organization.setEmail(attributes.getEmail());

        organizationRepository.save(organization);

        httpSession.setAttribute("user", new SessionUser(organization));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(organization.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
}
