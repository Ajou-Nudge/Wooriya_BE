package com.nudge.wooriya.application.port.out;

import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Individual;

public interface IndividualDAO {
    Individual join(Individual individual);

    UserInfoDto info();
}
