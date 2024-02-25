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
    private List<String> mainPhoto;
    private boolean verificationStatus;
    private Set<CafeAtmosphere> cafeAtmospheres;
    private String name;
    private String address;
    private Integer maximumOccupancy;
    private String yearsInBusiness;
    private Set<CollaborationPolicy> collaborationPolicy;
}
