package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.config.security.JwtTokenProvider;
import com.nudge.concent.config.security.SecurityUtil;
import com.nudge.concent.data.dao.MemberDAO;
import com.nudge.concent.data.dto.UserInfoDto;
import com.nudge.concent.data.entity.Member;
import com.nudge.concent.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDAOImpl implements MemberDAO {

    private final UserRepository userRepository;

    @Autowired
    public MemberDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Member join(Member member){
        Member savedMember = userRepository.save(member);
        return savedMember;
    }

    @Override
    public UserInfoDto info() {
        UserInfoDto userInfo = SecurityUtil.getCurrentMemberId();
        return userInfo;
    }
}
