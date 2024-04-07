package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.common.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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
        @Id
        private String id;
        // 수정 불가
        private String phoneNumber; // 전화번호
        private String email; // 이메일
        private String name; // 이름

        // 수정 가능
        private String introduction; // 소개, 회원가입 시 빈칸
        private String profilePhoto; // 프로필사진, 회원가입 시 빈칸
        private Set<String> relatedLink; // 관련 링크
        private String activityName; // 활동명, 회원가입 시 이름과 동일

        // 그 외
        private String password; // 비밀번호
        private Role role; // 역할

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
