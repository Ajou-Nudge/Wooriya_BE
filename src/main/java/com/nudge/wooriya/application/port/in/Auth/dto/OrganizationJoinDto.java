package com.nudge.wooriya.application.port.in.Auth.dto;

import com.nudge.wooriya.common.enums.OrganizationHistory;
import com.nudge.wooriya.common.enums.OrganizationKind;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrganizationJoinDto {
    private String email;
    private String password;
    private String organizationName;
    private String representativeName;
    private String representativeNum;
    private String representativeEmail;
    private OrganizationKind kind;
    private OrganizationHistory history;
    private String greetings;
}
