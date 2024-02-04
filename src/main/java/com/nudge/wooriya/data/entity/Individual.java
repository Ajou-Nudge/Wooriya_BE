package com.nudge.wooriya.data.entity;

import com.nudge.wooriya.data.dto.OAuthAttributesDto;
import com.nudge.wooriya.enums.AffiliateKind;
import com.nudge.wooriya.enums.OrganizationHistory;
import com.nudge.wooriya.enums.OrganizationKind;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Individual implements UserDetails {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String phoneNum;

    @Column(nullable = true)
    private String userId;

    @Column(nullable = true)
    private AffiliateKind kind;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "individual_pr_link", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<String> links;

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

    public Individual updateIndividualByOAuth(OAuthAttributesDto oAuthAttributesDto) {
        this.setName(oAuthAttributesDto.getName());
        this.setEmail(oAuthAttributesDto.getEmail());
        return this;
    }

    public static Individual createIndividualByOAuth(OAuthAttributesDto oAuthAttributesDto, String provider, String providerId) {
        Individual organization = new Individual();
        organization.setName(oAuthAttributesDto.getName());
        organization.setEmail(oAuthAttributesDto.getEmail());
        organization.setProvider(provider);
        organization.setProviderId(providerId);
        return organization;
    }
}
