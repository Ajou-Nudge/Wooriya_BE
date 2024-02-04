package com.nudge.wooriya.data.dao.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dao.IndividualDAO;
import com.nudge.wooriya.data.dao.OrganizationDAO;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Individual;
import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.data.repository.IndividualRepository;
import com.nudge.wooriya.data.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
