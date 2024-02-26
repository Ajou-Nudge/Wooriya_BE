package com.nudge.wooriya.data.entity;
import java.util.*;

import com.nudge.wooriya.enums.CollaborationPolicy;
import com.nudge.wooriya.enums.CafeAtmosphere;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;


@Document
@Data
@Getter
@Setter
public class Cafe implements UserDetails, OAuth2User {
    @Id
    private String cafeId; // 자동으로 UUID 값으로 넣음

    // 로그인 시에 받는 정보들
    private String email; // 이메일
    private String password; // 비밀번호
    private String phoneNumber; // 대표자 연락처
    private Set<String> relatedLinks; // 관련 링크
    private String tradeName; // 상호명
    private String cafeNumber; // 매장 전화번호
    private String yearsInBusiness; // 개업일
    private String address; // 주소
    private String cafeName; // 카페 이름

    // 나중에 추가 정보로 받을 정보들
    private List<String> mainPhotos; // 프로필 메인 사진
    private String introduction; // 소개글
    private Set<CafeAtmosphere> cafeAtmospheres; // 카페 분위기
    private Integer maximumOccupancy; // 최대 가용 인원수
    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 콜라보 형태
    private List<String> photos; // 사진
    private Set<String> relatedSNSLinks; // 관련 SNS 링크
    private String contactHours; // 연락 가능 시간
    private String holidays; // 휴일
    private List<String> profilePhoto; // 프로필 사진

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return this.cafeName;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(
                new SimpleGrantedAuthority("ROLE_CAFE")
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
