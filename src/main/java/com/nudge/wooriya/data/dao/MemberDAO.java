package com.nudge.wooriya.data.dao;

import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Member;

public interface MemberDAO {
    Member join(Member member);

    UserInfoDto info();
}
