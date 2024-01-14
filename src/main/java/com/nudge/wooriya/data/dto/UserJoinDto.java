package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserJoinDto {
    private String email;

    private String password;

    private String companyName;

    private String companyNum;

    private String representativeName;

    private String representativeNum;

    private CompanyKind kind;

    private CompanyHistory history;

    private String greetings;
}
