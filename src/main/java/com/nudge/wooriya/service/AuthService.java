package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.*;

public interface AuthService {

    TokenInfo login(LoginDto loginDto);

    Exception companyJoin(CompanyJoinDto companyJoinDto) throws Exception;

    Exception organizationJoin(OrganizationJoinDto organizationJoinDto) throws Exception;

    UserInfoDto info();
}