package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
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
