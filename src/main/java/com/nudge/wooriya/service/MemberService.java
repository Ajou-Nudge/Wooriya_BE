package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.member.MemberProfileDto;
import com.nudge.wooriya.data.dto.member.UpdateMemberProfileDto;
import com.nudge.wooriya.data.entity.Member;

import java.util.List;

public interface MemberService {
    MemberProfileDto getMemberByEmail(String email) throws Exception;
    UpdateMemberProfileDto updateMemberProfileByEmail(String email, UpdateMemberProfileDto updateMemberProfileDto) throws Exception;
}
