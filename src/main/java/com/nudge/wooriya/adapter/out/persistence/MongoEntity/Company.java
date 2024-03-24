package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.common.enums.CompanyHistory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.nudge.wooriya.common.enums.CompanyKind;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Company implements UserDetails, OAuth2User {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyAddress;

    @Column(nullable = false)
    private String representativeName;

    @Column(nullable = false)
    private String representativeNum;

    @Column(nullable = false)
    private CompanyKind kind;

    @Column(nullable = false)
    private CompanyHistory history;

    @Column(nullable = true)
    private String greetings;

    @Column(nullable = true)
    private String provider;

    @Column(nullable = true)
    private String providerId;

    public String getRole() {
        return "ROLE_COMPANY";
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(
                new SimpleGrantedAuthority("ROLE_COMPANY"),
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

    @Override
    public String getName() {
        return email;
    }
}
