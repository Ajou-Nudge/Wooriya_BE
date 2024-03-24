package com.nudge.wooriya.application.port.in.Cafe.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CollaborationPolicy;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class CafeDetailsDto {
    private String size; // 규모
    private String cafeOneLineIntroduction; // 카페 한줄소개
    private String introduction; // 메인 소개글
    private String purposeAnswer; // 목적 관련 사장님 답변, A
    private String collaborationPolicyAnswer; // 협업 관련 사장님 답변, B
    private String cafeAtmospheresAnswer; // 분위기 관련 사장님 답변, C
    private String hopePartnerAnswer; // 희망 협업 상대 관련 사장님 답변, D
    private Set<CafeAtmosphere> cafeAtmospheres; // 카페 분위기
    private String address; // 주소
    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 콜라보 형태
    private Integer maximumOccupancy; // 최대 가용 인원수
    private String yearsInBusiness; // 업력
    private List<String> photos; // 사진
    private String contactHours; // 연락 가능 시간
    private String holidays; // 휴일
    private String cafeName; // 카페 이름
    private String profilePhoto; // 카페 프로필 사진
    private Set<CafePreviewDto> keywordRelatedPreviews; // 최대 가용 인원
    private String email; // 협업 요청 보낼 떄, 사용하는 이메일
}




