package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.*;

public interface AuthService {

    TokenInfo login(LoginDto loginDto);

    Boolean companyJoin(CompanyJoinDto companyJoinDto) throws Exception;

    Boolean organizationJoin(OrganizationJoinDto organizationJoinDto) throws Exception;

    Boolean individualJoin(IndividualJoinDto individualJoinDto) throws Exception;

    UserInfoDto info();

    void oAuthJoin(OAuthJoinDto oAuthJoinDto);
}