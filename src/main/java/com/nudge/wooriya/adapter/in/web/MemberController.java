package com.nudge.wooriya.adapter.in.web;

import com.nudge.wooriya.application.port.in.Member.dto.MemberProfileDto;
import com.nudge.wooriya.application.port.in.Member.dto.UpdateMemberProfileDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Member;
import com.nudge.wooriya.application.port.in.Member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="User", description = "멤바 정보 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "멤버 정보 가져오기", description = "[Token X] 멤버 정보 가져오기")
    @GetMapping("/profile/{email}")
    public ResponseEntity<MemberProfileDto> memberProfile(@PathVariable String email) throws  Exception {
        MemberProfileDto memberProfileDto = memberService.getMemberByEmail(email);
        return ResponseEntity.ok(memberProfileDto);
    }

    @Operation(summary = "멤버 정보 수정하기", description = "[Token X] 멤버 정보 수정하기")
    @PostMapping("/profile/{email}")
    public ResponseEntity<UpdateMemberProfileDto> updateMemberProfile(@PathVariable String email, @RequestBody UpdateMemberProfileDto updateMemberProfile ) throws  Exception {
        UpdateMemberProfileDto updateMemberProfileDto = memberService.updateMemberProfileByEmail(email, updateMemberProfile);
        return ResponseEntity.ok(updateMemberProfileDto);
    }

    @Operation(summary = "[관리자] 멤버 정보 추가하기", description = "[Token X] 멤버 정보 추가하기")
    @PostMapping("/admin/profile")
    public ResponseEntity<String> postMemberProfile(@RequestBody Member MemberProfile ) throws  Exception {
        String result = memberService.addMemberProfile(MemberProfile);
        return ResponseEntity.ok(result);
    }


}


