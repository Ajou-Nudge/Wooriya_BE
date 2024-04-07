package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.common.enums.AffiliateKind;
import com.nudge.wooriya.common.OAuth.dto.OAuthAttributesDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "individuals") // MongoDB 컬렉션 지정
@Getter
@Setter
public class Individual implements UserDetails {
    @Id
    private String id;

    private String email;

    private String password;

    private String name;

    private String phoneNum;

    private String userId;

    private AffiliateKind kind;

    private Set<String> links; // MongoDB에서는 직접적으로 Set을 사용할 수 있습니다.

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

    // UserDetails 인터페이스 구현은 boolean 값들을 실제 비즈니스 로직에 맞게 조정해야 합니다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Individual updateIndividualByOAuth(OAuthAttributesDto oAuthAttributesDto) {
        this.name = oAuthAttributesDto.getName();
        this.email = oAuthAttributesDto.getEmail();
        return this;
    }

    public static Individual createIndividualByOAuth(OAuthAttributesDto oAuthAttributesDto, String provider, String providerId) {
        return Individual.builder()
                .name(oAuthAttributesDto.getName())
                .email(oAuthAttributesDto.getEmail())
                .provider(provider)
                .providerId(providerId)
                .build();
    }
}
