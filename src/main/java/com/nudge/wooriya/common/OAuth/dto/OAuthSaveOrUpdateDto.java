package com.nudge.wooriya.common.OAuth.dto;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Organization;
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
