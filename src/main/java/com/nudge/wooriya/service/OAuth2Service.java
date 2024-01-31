package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.GoogleOAuthToken;
import com.nudge.wooriya.data.dto.OAuthAttributesDto;
import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.data.entity.SessionUser;
import com.nudge.wooriya.data.repository.OrganizationRepository;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OrganizationRepository organizationRepository;
    private final HttpSession httpSession;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String cliendId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @Autowired
    public OAuth2Service(OrganizationRepository organizationRepository, HttpSession httpSession, JwtTokenProvider jwtTokenProvider) {
        this.organizationRepository = organizationRepository;
        this.httpSession = httpSession;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributesDto attributes = OAuthAttributesDto.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Organization organization = organizationRepository.findByEmail(attributes.getEmail()).orElse(new Organization());
        if(organization.getEmail() == null) {
            organization = saveOrUpdate(attributes, registrationId);
        } else {
            organization.setProvider(registrationId);
        }

        httpSession.setAttribute("user", new SessionUser(organization));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(organization.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Organization saveOrUpdate(OAuthAttributesDto oAuthAttributesDto, String registrationId) {
        Organization newOrganization = organizationRepository.findByEmailAndProvider(oAuthAttributesDto.getEmail(), registrationId)
                .map(o -> o.updateOrganizationByOAuth(oAuthAttributesDto.getName(), oAuthAttributesDto.getEmail()))
                .orElse(Organization.createOrganizationByOAuth(oAuthAttributesDto.getName(), oAuthAttributesDto.getEmail(), registrationId, registrationId));

        return organizationRepository.save(newOrganization);
    }

    public TokenInfo oAuthLogin(String code) {
        String GOOGLE_TOKEN_REQUEST_URL = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", cliendId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "authorization_code");
        ResponseEntity<String> accessToken = restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL, params, String.class);
        JSONObject googleOAuthToken = new JSONObject(accessToken.getBody());

        String GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+googleOAuthToken.getString("access_token"));
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> userInfoResponse = restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, request, String.class);

        OAuthAttributesDto oAuthAttributesDto = new OAuthAttributesDto().parse(userInfoResponse.getBody());
        Organization organization = saveOrUpdate(oAuthAttributesDto, "google");

        Authentication authentication = new UsernamePasswordAuthenticationToken(organization.getUsername(), organization.getPassword(), organization.getAuthorities());
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        tokenInfo.setEmail(organization.getEmail());
        tokenInfo.setMemberRole(organization.getRole());
        return tokenInfo;
    }

}
