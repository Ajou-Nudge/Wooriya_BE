package com.nudge.wooriya.adapter.out.persistence.MongoEntity;
import java.util.*;

import com.nudge.wooriya.common.enums.CafeSpot;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.ContactType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
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
    private String cafeId;
    private long viewCount; // 조회수
    // 로그인 시에 받는 정보들
    private String email; // 이메일
    private String password; // 비밀번호
    private String phoneNumber; // 대표자 연락처
    private ContactType contactType; // 희망 연락 수단, INSTARGRAM, MESSAGE, CALL
    private String instagramLink; // 인스타그램 링크, null 일수도 있음
    private String tradeName; // 상호명
    private Integer yearsInBusiness; // 업력
    private String address; // 주소
    private String cafeName; // 카페 이름

    // 나중에 추가 정보로 받을 정보들
    @Field("size")
    @Indexed
    private double size; // 카페 규모 ex) 평수

    @Field("cafeAtmospheres")
    @Indexed
    private Set<CafeAtmosphere> cafeAtmospheres; // 카페 분위기

    @Field("maximumOccupancy")
    @Indexed
    private int maximumOccupancy; // 최대 가용 인원수

    @Field("cafeSpot")
    @Indexed
    private CafeSpot cafeSpot; // 카페 스팟

    private String cafeOneLineIntroduction; // 카페 한줄소개
    private String introduction; // 소개글
    private String purposeAnswer; // 목적 관련 사장님 답변, A
    private String collaborationPolicyAnswer; // 협업 관련 사장님 답변, B
    private String cafeAtmospheresAnswer; // 분위기 관련 사장님 답변, C
    private String hopePartnerAnswer; // 희망 협업 상대 관련 사장님 답변, D

    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 콜라보 형태
    private List<String> photos; // 사진, 첫번째 사진이 메인 사진
    private String contactHours; // 연락 가능 시간
    private String holidays; // 휴일
    private String profilePhoto; // 프로필 사진

    // 시간 정보
    @Field("createdAt")
    @Indexed(direction = IndexDirection.DESCENDING)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

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
