package com.nudge.wooriya.application.port.in.Auth.dto;

import com.nudge.wooriya.common.enums.AffiliateKind;
import com.nudge.wooriya.common.enums.CompanyHistory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IndividualJoinDto {
    private String email;
    private String userId;
    private String password;
    private String name;
    private String phoneNum;
    private AffiliateKind kind;
    private CompanyHistory history;
    private String[] links;
}
