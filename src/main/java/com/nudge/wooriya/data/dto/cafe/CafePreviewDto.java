package com.nudge.wooriya.data.dto.cafe;

import com.nudge.wooriya.enums.CollaborationPolicy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.nudge.wooriya.enums.CafeAtmosphere;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class CafePreviewDto {
    private String mainPhoto; // 미리보기 사진
    private Set<CafeAtmosphere> cafeAtmospheres; // 카페 분위기
    private String cafeName; // 카페이름
    private String address; // 주소
    private Integer maximumOccupancy; // 최대 수용 인원
    private String yearsInBusiness; // 업력
    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 협업 방식
}
