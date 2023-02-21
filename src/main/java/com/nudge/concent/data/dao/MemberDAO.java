package com.nudge.concent.data.dao;

import com.nudge.concent.data.dto.UserInfoDto;
import com.nudge.concent.data.entity.Member;

public interface MemberDAO {
    Member join(Member member);

    UserInfoDto info();
}
