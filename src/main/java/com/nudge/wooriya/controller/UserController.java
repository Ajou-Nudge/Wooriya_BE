package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.data.entity.Proposal;
import com.nudge.wooriya.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="User", description = "유저 프로필/알림 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "profile", description = "[Token X] 이메일에 해당하는 프로필 조회")
    @GetMapping("/profile/{email}")
    public ResponseEntity<ProfileDto> profile(@PathVariable String email) throws Exception {
        ProfileDto profileDto = userService.profile(email);
        return ResponseEntity.status(HttpStatus.OK).body(profileDto);
    }

    @Operation(summary = "getAllNotification", description = "[Token O] 전체 알림 조회")
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotification() throws Exception {
        List<NotificationResponseDto> notificationResponseDtos = userService.getAllNotification();
        return ResponseEntity.status(HttpStatus.OK).body(notificationResponseDtos);
    }

    @Operation(summary = "readNotification", description = "[Token O] 알림 읽음 처리")
    @PostMapping("/notification/read/{id}")
    public ResponseEntity<Boolean> readNotification(@PathVariable Long id) throws Exception {
        Boolean result = userService.readNotification(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "selectProposal", description = "[Token O] 제휴 제안 승인/거절")
    @PostMapping("/proposal/select")
    public ResponseEntity<Boolean> selectProposal(@RequestBody ProposalSelectDto proposalSelectDto) throws Exception {
        Boolean result = userService.selectProposal(proposalSelectDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "sendProposal", description = "[Token O] 보낸 제휴 제안")
    @GetMapping("/proposal/send")
    public ResponseEntity<List<ProposalProfileDto>> sendProposal() throws Exception {
        List<ProposalProfileDto> proposals = userService.sendProposal();
        return ResponseEntity.status(HttpStatus.OK).body(proposals);
    }

    @Operation(summary = "receiveProposal", description = "[Token O] 받은 제휴 제안")
    @GetMapping("/proposal/receive")
    public ResponseEntity<List<ProposalProfileDto>> receiveProposal() throws Exception {
        List<ProposalProfileDto> proposals = userService.receiveProposal();
        return ResponseEntity.status(HttpStatus.OK).body(proposals);
    }
}