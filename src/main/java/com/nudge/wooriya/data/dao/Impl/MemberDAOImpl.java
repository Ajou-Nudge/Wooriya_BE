package com.nudge.wooriya.data.dao.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dao.MemberDAO;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Member;
import com.nudge.wooriya.data.repository.UserRepository;
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
