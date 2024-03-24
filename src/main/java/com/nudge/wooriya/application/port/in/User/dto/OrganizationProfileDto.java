package com.nudge.wooriya.application.port.in.User.dto;

import com.nudge.wooriya.common.enums.OrganizationHistory;
import com.nudge.wooriya.common.enums.OrganizationKind;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrganizationProfileDto {
    private String email;
    private String organizationName;
    private String representativeName;
    private String representativeNum;
    private OrganizationKind kind;
    private OrganizationHistory history;
    private String representativeEmail;
    private String greetings;
}
