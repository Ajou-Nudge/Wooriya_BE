package com.nudge.wooriya.application.port.in.User.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProfileDto {
    private String email;
    private Boolean isCompany;
    private OrganizationProfileDto organizationProfileDto;
    private CompanyProfileDto companyProfileDto;
}
