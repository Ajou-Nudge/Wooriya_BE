package com.nudge.wooriya.adapter.out.persistence.Impl;

import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.application.port.out.IndividualDAO;
import com.nudge.wooriya.application.port.in.Auth.dto.UserInfoDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Individual;
import com.nudge.wooriya.adapter.out.persistence.Repo.IndividualRepository;
import org.springframework.stereotype.Component;

@Component
public class IndividualDAOImpl implements IndividualDAO {

    private final IndividualRepository individualRepository;

    public IndividualDAOImpl(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }


    @Override
    public Individual join(Individual individual){
        Individual savedIndividual = individualRepository.save(individual);
        return savedIndividual;
    }

    @Override
    public UserInfoDto info() {
        UserInfoDto userInfo = SecurityUtil.getCurrentMemberId();
        return userInfo;
    }
}
