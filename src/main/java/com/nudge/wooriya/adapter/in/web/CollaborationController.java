package com.nudge.wooriya.adapter.in.web;

import com.nudge.wooriya.application.port.in.Collabo.dto.ProposalApproveDto;
import com.nudge.wooriya.application.port.in.Collabo.dto.ProposalNotificationDto;
import com.nudge.wooriya.application.port.in.Collabo.dto.ProposalRequestNotificationDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Collabo.ProposalRequest;
import com.nudge.wooriya.application.port.in.Collabo.CollaborationUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Collaboration", description = "협업 정보 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collabo")
public class CollaborationController {

    private final CollaborationUsecase collaborationService;

//    @PostMapping("/postings")
//    public ResponseEntity<Collaboration> postCollaboration(@RequestBody Collaboration collaboration) throws Exception {
//        Collaboration result = collaborationService.postCollaborationPosting(collaboration);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/previews")
//    public ResponseEntity<List<CollaborationPreviewDto>> getCollaborationPreviews() {
//        List<CollaborationPreviewDto> result = collaborationService.getCollaborationPreview();
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/proposals/{id}")
//    public ResponseEntity<CollaborationProposalDto> getCollaborationProposalById(@PathVariable String id) {
//        CollaborationProposalDto result = collaborationService.getCollaborationProposalById(id);
//        return ResponseEntity.ok(result);
//    }

    @Operation(summary = "협업 요청 보내기", description = "[Token X] 특정 카페한테 협업 요청 보내기")
    @PostMapping("/proposal-requests") //
    public ResponseEntity<String> postProposalRequest(@RequestBody ProposalRequestNotificationDto proposalRequest) throws Exception {
        String result = collaborationService.postProposalRequest(proposalRequest);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "해당 email을 쓰는 cafe로 온 협업 요청 알림", description = "[Token X] 알림창에 내가 받은 협업 요청 데이터 가져오기")
    @GetMapping("/proposal-notifications/{email}") //
    public ResponseEntity<List<ProposalNotificationDto>> getProposalNotifications(@PathVariable String email) throws Exception {
        List<ProposalNotificationDto> result = collaborationService.postProposalnotification(email);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "협업 요청 알림 읽었다고 전송", description = "[Token X] 협업 요청 알림 읽었다고 전송")
    @PostMapping("/proposal-notifications/read/{id}") //
    public ResponseEntity<String> postProposalNotificationsRead(@PathVariable String id) throws Exception {
        String result = collaborationService.proposalnotificationRead(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "협업 이메일 발송 요청 대기 중 리스트", description = "[Token X] 협업 요청에 대해서 승인 받지 않은 것들을 보여주기")
    @GetMapping("/admin/proposal")
    public ResponseEntity<List<ProposalRequest>> getProposalNotifications(){
        List<ProposalRequest> proposalRequests = collaborationService.getAllProposalRequests();
        return ResponseEntity.ok(proposalRequests);
    }

    @Operation(summary = "협업 이메일 발송 요청 승인", description = "[Token X] 협업 요청에 대해서 해당 id 값으로 승인")
    @PostMapping("/admin/approve")
    public ResponseEntity<String> postApproveProposalRequest(@RequestBody ProposalApproveDto proposalApproveDto ){
        String result = collaborationService.postApproveProposalRequest(proposalApproveDto);
        return ResponseEntity.ok(result);
    }

}
