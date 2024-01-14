package com.nudge.wooriya.data.dto;

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
