package com.nudge.wooriya.data.dto.cafe;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.nudge.wooriya.enums.CafeAtmosphere;
import com.nudge.wooriya.enums.CollaborationPolicy;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class CafeDetailsDto {
    private String introduction; // 소개글
    private Set<CafeAtmosphere> cafeAtmosphere; // 카페 분위기
    private String address; // 주소
    private Integer maximumOccupancy; // 최대 가용 인원수
    private String yearsInBusiness; // 업력
    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 콜라보 형태
    private List<String> photos; // 사진
    private Set<String> relatedSNSLinks;
    private String contactHours;
    private String holidays;
    private Set<CafePreviewDto> keywordRelatedPreviews;
}

