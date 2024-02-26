package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.ProfileDto;
import com.nudge.wooriya.data.dto.member.MemberProfileDto;
import com.nudge.wooriya.data.dto.member.UpdateMemberProfileDto;
import com.nudge.wooriya.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="User", description = "카페 정보 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "카페 상세 페이지", description = "[Token X] 카페 상세정보 가져오기")
    @GetMapping("/profile/{email}")
    public ResponseEntity<MemberProfileDto> memberProfile(@PathVariable String email) throws  Exception {
        MemberProfileDto memberProfileDto = memberService.getMemberByEmail(email);
        return ResponseEntity.ok(memberProfileDto);
    }

    @Operation(summary = "카페 상세 페이지", description = "[Token X] 카페 상세정보 가져오기")
    @PostMapping("/profile/{email}")
    public ResponseEntity<UpdateMemberProfileDto> updateMemberProfile(@PathVariable String email, @RequestBody UpdateMemberProfileDto updateMemberProfile ) throws  Exception {
        UpdateMemberProfileDto updateMemberProfileDto = memberService.updateMemberProfileByEmail(email, updateMemberProfile);
        return ResponseEntity.ok(updateMemberProfileDto);
    }
}


