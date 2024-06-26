package com.nudge.wooriya.application.port.in.Auth;

import com.nudge.wooriya.application.port.in.Auth.dto.*;
import com.nudge.wooriya.common.OAuth.dto.OAuthJoinDto;
import com.nudge.wooriya.common.config.security.TokenInfo;


public interface AuthUsecase {

    TokenInfo Userlogin(UserLoginDto userLoginDto);

    Boolean individualJoin(IndividualJoinDto individualJoinDto) throws Exception;

    UserInfoDto Userinfo();

    void oAuthJoin(OAuthJoinDto oAuthJoinDto);

}