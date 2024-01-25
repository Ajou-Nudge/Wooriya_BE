package com.nudge.wooriya.data.entity;

import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
import com.nudge.wooriya.enums.OrganizationHistory;
import com.nudge.wooriya.enums.OrganizationKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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

    public Organization updateOrganizationByOAuth(String organizationName, String email) {
        this.setOrganizationName(organizationName);
        this.setEmail(email);
        return this;
    }

    public static Organization createOrganizationByOAuth(String organizationName, String email, String provider, String providerId) {
        Organization organization = new Organization();
        organization.setOrganizationName(organizationName);
        organization.setEmail(email);
        organization.setProvider(provider);
        organization.setProviderId(providerId);
        return organization;
    }
}
