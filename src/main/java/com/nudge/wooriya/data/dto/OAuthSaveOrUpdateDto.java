package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.enums.OrganizationHistory;
import com.nudge.wooriya.enums.OrganizationKind;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OAuthSaveOrUpdateDto {
    Organization organization;
    Boolean isOAuthRegister;
}
