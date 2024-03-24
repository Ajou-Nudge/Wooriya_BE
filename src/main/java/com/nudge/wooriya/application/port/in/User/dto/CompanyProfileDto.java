package com.nudge.wooriya.application.port.in.User.dto;

import com.nudge.wooriya.common.enums.CompanyHistory;
import com.nudge.wooriya.common.enums.CompanyKind;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CompanyProfileDto {
    private String email;
    private String companyName;
    private String representativeName;
    private String representativeNum;
    private CompanyKind kind;
    private CompanyHistory history;
    private String greetings;
}
