package com.nudge.wooriya.application.port.out;

import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Company;

public interface CompanyDAO {
    Company join(Company company);

    UserInfoDto info();
}