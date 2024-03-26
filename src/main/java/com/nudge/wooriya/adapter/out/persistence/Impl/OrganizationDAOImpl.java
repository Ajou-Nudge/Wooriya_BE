package com.nudge.wooriya.adapter.out.persistence.Impl;

import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.application.port.out.OrganizationDAO;
import com.nudge.wooriya.application.port.in.Auth.dto.UserInfoDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Organization;
import com.nudge.wooriya.adapter.out.persistence.Repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationDAOImpl implements OrganizationDAO {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationDAOImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization join(Organization organization){
        Organization savedOrganization = organizationRepository.save(organization);
        return savedOrganization;
    }

    @Override
    public UserInfoDto info() {
        UserInfoDto userInfo = SecurityUtil.getCurrentMemberId();
        return userInfo;
    }
}
