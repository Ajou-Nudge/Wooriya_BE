package com.nudge.wooriya.application.port.in.Cafe.dto;

import com.nudge.wooriya.common.enums.CollaborationPolicy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class CafeProfileDto {
    private String tradeName;
    private String cafeName;
    private String address;
    private Integer maximumOccupancy;
    private Set<CollaborationPolicy> collaborationPolicy;
    private String introduction;
    private List<String> profilePhoto;
    private Set<String> relatedLinks;
    private String contactHours;
    private String holidays;
}
