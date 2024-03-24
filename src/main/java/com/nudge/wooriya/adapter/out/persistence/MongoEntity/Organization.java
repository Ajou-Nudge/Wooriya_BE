package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.data.dto.OAuthAttributesDto;
import com.nudge.wooriya.common.enums.OrganizationHistory;
import com.nudge.wooriya.common.enums.OrganizationKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Organization implements UserDetails {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String organizationName;

    @Column(nullable = true)
    private String representativeName;

    @Column(nullable = true)
    private String representativeNum;

    @Column(nullable = true)
    private String representativeEmail;

    @Column(nullable = true)
    private OrganizationKind kind;

    @Column(nullable = true)
    private OrganizationHistory history;

    @Column(nullable = true)
    private String greetings;

    @Column(nullable = true)
    private String provider;

    @Column(nullable = true)
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
