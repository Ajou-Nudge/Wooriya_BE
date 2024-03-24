package com.nudge.wooriya.adapter.out.persistence.Impl;

import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.application.port.out.CompanyDAO;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Company;
import com.nudge.wooriya.adapter.out.persistence.Repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyDAOImpl implements CompanyDAO {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyDAOImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company join(Company company){
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }

    @Override
    public UserInfoDto info() {
        UserInfoDto userInfo = SecurityUtil.getCurrentMemberId();
        return userInfo;
    }
}