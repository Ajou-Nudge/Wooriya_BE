package com.nudge.concent.service;

import com.nudge.concent.config.security.TokenInfo;
import com.nudge.concent.data.dto.UserInfoDto;
import com.nudge.concent.data.dto.UserJoinDto;
import com.nudge.concent.data.dto.UserLoginDto;
import com.nudge.concent.data.entity.Member;

public interface UserService {

    TokenInfo login(UserLoginDto userLoginDto);

    Member join(UserJoinDto userJoinDto);

    UserInfoDto info();
}