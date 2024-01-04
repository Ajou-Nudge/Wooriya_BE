package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.UserJoinDto;
import com.nudge.wooriya.data.dto.UserLoginDto;

public interface AuthService {

    TokenInfo login(UserLoginDto userLoginDto);

    Exception join(UserJoinDto userJoinDto) throws Exception;

}