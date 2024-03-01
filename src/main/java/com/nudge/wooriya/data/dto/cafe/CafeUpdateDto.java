package com.nudge.wooriya.data.dto.cafe;

import com.nudge.wooriya.enums.CafeAtmosphere;
import com.nudge.wooriya.enums.CollaborationPolicy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class CafeUpdateDto {
    private String size; // 카페 규모 ex) 평수
    private String cafeOneLineIntroduction; // 카페 한줄소개
    private String introduction; // 소개글
    private String purposeAnswer; // 목적 관련 사장님 답변, A
    private String collaborationPolicyAnswer; // 협업 관련 사장님 답변, B
    private String cafeAtmospheresAnswer; // 분위기 관련 사장님 답변, C
    private String hopePartnerAnswer; // 희망 협업 상대 관련 사장님 답변, D
    private Set<CafeAtmosphere> cafeAtmospheres; // 카페 분위기
    private Integer maximumOccupancy; // 최대 가용 인원수
    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 콜라보 형태
    private List<String> photos; // 사진, 첫번째 사진이 메인 사진
    private String contactHours; // 연락 가능 시간
    private String holidays; // 휴일
    private String profilePhoto; // 프로필 사진
}
