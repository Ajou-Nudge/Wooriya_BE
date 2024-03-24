package com.nudge.wooriya.application.port.in.Auth.dto;

import com.nudge.wooriya.common.enums.CompanyHistory;
import com.nudge.wooriya.common.enums.CompanyKind;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CompanyJoinDto {
    private String email;
    private String password;
    private String companyName;
    private String companyAddress;
    private String representativeName;
    private String representativeNum;
    private CompanyKind kind;
    private CompanyHistory history;
    private String greetings;
}
