package com.nudge.wooriya.common.OAuth.dto;

import com.nudge.wooriya.application.port.in.Auth.dto.CompanyJoinDto;
import com.nudge.wooriya.application.port.in.Auth.dto.IndividualJoinDto;
import com.nudge.wooriya.application.port.in.Auth.dto.OrganizationJoinDto;
import com.nudge.wooriya.common.enums.MemberType;
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
