package com.nudge.wooriya.data.dao.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dao.OrganizationDAO;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.data.repository.OrganizationRepository;
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
