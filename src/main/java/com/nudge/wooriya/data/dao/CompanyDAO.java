package com.nudge.wooriya.data.dao;

import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Company;

public interface CompanyDAO {
    Company join(Company company);

    UserInfoDto info();
}
