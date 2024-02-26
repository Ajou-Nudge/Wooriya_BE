package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.data.dto.member.MemberProfileDto;
import com.nudge.wooriya.data.dto.member.UpdateMemberProfileDto;
import com.nudge.wooriya.data.entity.Member;
import com.nudge.wooriya.data.repository.MemberRepository;
import com.nudge.wooriya.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
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

        member.setName(updateMemberProfileDto.getName());
        member.setIntroduction(updateMemberProfileDto.getIntroduction());
        member.setRelatedLink(updateMemberProfileDto.getRelatedLink());
        member.setProfilePhoto(updateMemberProfileDto.getProfilePhoto());

        // 업데이트된 회원 정보를 저장
        Member updatedMember = memberRepository.save(member);

        // 업데이트된 회원 정보로 UpdateMemberProfileDto 생성 및 반환
        UpdateMemberProfileDto updatedProfileDto = new UpdateMemberProfileDto();
        updatedProfileDto.setName(updatedMember.getName());
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
        memberProfileDto.setRole(member.getRole());
        return memberProfileDto;
    }


}
