package com.nudge.wooriya.data.entity;

import com.nudge.wooriya.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Document
@Data
@Getter
@Setter
public class Member implements UserDetails, OAuth2User {
        private String phoneNumber;
        private String email;
        private String password;
        private String introduction;
        private String profilePhoto;
        private String name;
        private Set<String> relatedLink;
        private Role role;

        @Override
        public Map<String, Object> getAttributes() {
                return null;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(
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

}
