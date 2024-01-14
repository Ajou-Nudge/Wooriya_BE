package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.CompanyJoinDto;
import com.nudge.wooriya.data.dto.LoginDto;
import com.nudge.wooriya.data.dto.OrganizationJoinDto;
import com.nudge.wooriya.data.dto.UserJoinDto;

public interface AuthService {

    TokenInfo login(LoginDto loginDto);

    Exception companyJoin(CompanyJoinDto companyJoinDto) throws Exception;

    Exception organizationJoin(OrganizationJoinDto organizationJoinDto) throws Exception;
}