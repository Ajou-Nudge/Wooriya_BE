package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
import com.nudge.wooriya.enums.MemberType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OAuthJoinDto {

    private MemberType memberType;

    private OrganizationJoinDto organizationJoinDto;

    private CompanyJoinDto companyJoinDto;

    private IndividualJoinDto individualJoinDto;
}
