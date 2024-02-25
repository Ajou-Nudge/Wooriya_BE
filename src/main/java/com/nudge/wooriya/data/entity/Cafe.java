package com.nudge.wooriya.data.entity;
import java.util.List;
import java.util.Set;
import com.nudge.wooriya.enums.CollaborationPolicy;
import com.nudge.wooriya.enums.CafeAtmosphere;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
@Getter
@Setter
public class Cafe {
    @Id
    private String cafeId;

    private String cafeName;
    private List<String> mainPhotos; // 프로필 메인 사진
    private String introduction; // 소개글
    private Set<CafeAtmosphere> cafeAtmospheres; // 카페 분위기
    private String address; // 주소
    private Integer maximumOccupancy; // 최대 가용 인원수
    private String yearsInBusiness; // 업력
    private Set<CollaborationPolicy> collaborationPolicy; // 원하는 콜라보 형태
    private List<String> photos; // 사진
    private Set<String> relatedSNSLinks; // 관련 SNS 링크
    private String contactHours; // 연락 가능 시간
    private String holidays; // 휴일
    private String tradeName; // 상호명
    private List<String> profilePhoto; // 프로필 사진
    private Set<String> relatedLinks; // 관련 링크
}
