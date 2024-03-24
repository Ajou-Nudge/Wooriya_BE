package com.nudge.wooriya.application.port.in.Member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UpdateMemberProfileDto {
    private String introduction;
    private String profilePhoto;
    private String activityName;
    private Set<String> relatedLink;
}
