package com.nudge.wooriya.application.port.out;

import com.nudge.wooriya.application.port.in.Auth.dto.UserInfoDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Organization;

public interface OrganizationDAO {
    Organization join(Organization organization);

    UserInfoDto info();
}
