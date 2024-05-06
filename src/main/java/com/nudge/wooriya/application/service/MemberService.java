package com.nudge.wooriya.application.service;

import com.nudge.wooriya.application.port.in.Member.dto.MemberProfileDto;
import com.nudge.wooriya.application.port.in.Member.dto.UpdateMemberProfileDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Member;
import com.nudge.wooriya.adapter.out.persistence.Repo.MemberRepository;
import com.nudge.wooriya.application.port.in.Member.MemberUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements MemberUsecase {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberProfileDto getMemberByEmail(String email) throws Exception {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {
            return convertToMemberProfile(memberOptional.get());
        } else {
            throw new Exception("Member not found with email: " + email);
        }
    }

    @Override
    public UpdateMemberProfileDto updateMemberProfileByEmail(String email, UpdateMemberProfileDto updateMemberProfileDto) throws Exception {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if (!memberOptional.isPresent()) {
            throw new Exception("Member not found with email: " + email);
        }

        Member member = memberOptional.get();

        member.setName(updateMemberProfileDto.getActivityName());
        member.setIntroduction(updateMemberProfileDto.getIntroduction());
        member.setRelatedLink(updateMemberProfileDto.getRelatedLink());
        member.setProfilePhoto(updateMemberProfileDto.getProfilePhoto());

        // 업데이트된 회원 정보를 저장
        Member updatedMember = memberRepository.save(member);

        // 업데이트된 회원 정보로 UpdateMemberProfileDto 생성 및 반환
        UpdateMemberProfileDto updatedProfileDto = new UpdateMemberProfileDto();
        updatedProfileDto.setActivityName(updatedMember.getName());
        updatedProfileDto.setIntroduction(updatedMember.getIntroduction());
        updatedProfileDto.setRelatedLink(updatedMember.getRelatedLink());
        updatedProfileDto.setProfilePhoto(updatedMember.getProfilePhoto());

        return updatedProfileDto;
    }

    private MemberProfileDto convertToMemberProfile(Member member) {
        MemberProfileDto memberProfileDto = new MemberProfileDto();
        memberProfileDto.setEmail(member.getEmail());
        memberProfileDto.setName(member.getName());
        memberProfileDto.setRelatedLink(member.getRelatedLink());
        memberProfileDto.setIntroduction(member.getIntroduction());
        memberProfileDto.setPhoneNumber(member.getPhoneNumber());
        memberProfileDto.setProfilePhoto(member.getProfilePhoto());
        return memberProfileDto;
    }

    @Override
    public String addMemberProfile(Member member)  {
        memberRepository.save(member);
        return "success";
    }

}
