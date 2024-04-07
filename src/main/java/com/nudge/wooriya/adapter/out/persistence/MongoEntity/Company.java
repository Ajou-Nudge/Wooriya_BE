package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.common.enums.CompanyHistory;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "companies") // MongoDB 컬렉션 명시
@Getter
@Setter
public class Company implements UserDetails, OAuth2User {
    @Id
    private String id;

    private String email;

    private String password;

    private String companyName;

    private String companyAddress;

    private String representativeName;

    private String representativeNum;


    private CompanyKind kind;


    private CompanyHistory history;


    private String greetings;


    private String provider;


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
