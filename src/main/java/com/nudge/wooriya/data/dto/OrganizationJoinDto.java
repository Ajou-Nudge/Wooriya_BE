package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
import com.nudge.wooriya.enums.OrganizationHistory;
import com.nudge.wooriya.enums.OrganizationKind;
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

    private String organizationEmail;

    private OrganizationKind kind;

    private OrganizationHistory history;

    private String greetings;
}
