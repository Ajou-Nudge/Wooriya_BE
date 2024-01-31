package com.nudge.wooriya.data.dao;

import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Organization;

public interface OrganizationDAO {
    Organization join(Organization organization);

    UserInfoDto info();
}
