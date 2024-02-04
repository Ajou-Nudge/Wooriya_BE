package com.nudge.wooriya.data.dao;

import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Individual;
import com.nudge.wooriya.data.entity.Organization;

public interface IndividualDAO {
    Individual join(Individual individual);

    UserInfoDto info();
}
