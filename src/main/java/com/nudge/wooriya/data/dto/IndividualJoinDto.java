package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.enums.AffiliateKind;
import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
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
