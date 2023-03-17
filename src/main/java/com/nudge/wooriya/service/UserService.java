package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.dto.UserJoinDto;
import com.nudge.wooriya.data.dto.UserLoginDto;
import com.nudge.wooriya.data.entity.Member;

public interface UserService {

    TokenInfo login(UserLoginDto userLoginDto);

    Member join(UserJoinDto userJoinDto);

    UserInfoDto info();
}