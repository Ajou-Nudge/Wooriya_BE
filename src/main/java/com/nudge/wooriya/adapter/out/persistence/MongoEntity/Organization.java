package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.common.OAuth.dto.*;
import com.nudge.wooriya.common.enums.OrganizationHistory;
import com.nudge.wooriya.common.enums.OrganizationKind;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Setter
@Getter
public class Organization implements UserDetails {
    @Id
    private String id;

    private String email;


    private String password;

    private String organizationName;

    private String representativeName;

    private String representativeNum;


    private String representativeEmail;


    private OrganizationKind kind;


    private OrganizationHistory history;


    private String greetings;


    private String provider;


    private String providerId;

    public String getRole() {
        return "ROLE_ORG";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ORG"),
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Organization updateOrganizationByOAuth(OAuthAttributesDto oAuthAttributesDto) {
        this.setOrganizationName(oAuthAttributesDto.getName());
        this.setEmail(oAuthAttributesDto.getEmail());
        return this;
    }

    public static Organization createOrganizationByOAuth(OAuthAttributesDto oAuthAttributesDto, String provider, String providerId) {
        Organization organization = new Organization();
        organization.setOrganizationName(oAuthAttributesDto.getName());
        organization.setEmail(oAuthAttributesDto.getEmail());
        organization.setProvider(provider);
        organization.setProviderId(providerId);
        return organization;
    }
}
