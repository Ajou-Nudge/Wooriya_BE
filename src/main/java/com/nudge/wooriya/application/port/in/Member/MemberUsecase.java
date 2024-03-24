package com.nudge.wooriya.application.port.in.Member;

import com.nudge.wooriya.application.port.in.Member.dto.MemberProfileDto;
import com.nudge.wooriya.application.port.in.Member.dto.UpdateMemberProfileDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Member;

public interface MemberUsecase {
    MemberProfileDto getMemberByEmail(String email) throws Exception;
    UpdateMemberProfileDto updateMemberProfileByEmail(String email, UpdateMemberProfileDto updateMemberProfileDto) throws Exception;
    String addMemberProfile(Member member);
}
