package com.nudge.wooriya.service;

import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.dto.UserJoinDto;
import com.nudge.wooriya.data.dto.UserLoginDto;
import com.nudge.wooriya.data.entity.Member;

public interface MailService {
    String sendMail(String mailAddress);

    String sendConfirmCode(String mailAddress);

    String verifyConfirmCode(String confirmCode) throws Exception;
}