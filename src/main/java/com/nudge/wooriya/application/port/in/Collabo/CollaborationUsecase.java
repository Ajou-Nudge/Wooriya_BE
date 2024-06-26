package com.nudge.wooriya.application.port.in.Collabo;

import com.nudge.wooriya.application.port.in.Collabo.dto.*;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Collabo.Collaboration;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Collabo.ProposalRequest;

import java.util.List;


public interface CollaborationUsecase {
    Collaboration postCollaborationPosting(Collaboration collaboration) throws Exception; // 협업 제안 글 쓰

    List<CollaborationPreviewDto> getCollaborationPreview(); // 협업 제안 미리보기

    CollaborationProposalDto getCollaborationProposalById(String id); // 협업 제안 글 보여주기기

    String postProposalRequest(ProposalRequestNotificationDto proposalRequest) throws Exception;

    List<ProposalNotificationDto> postProposalnotification(String email) throws Exception;

    List<ProposalRequest> getAllProposalRequests();

    String postApproveProposalRequest(ProposalApproveDto proposalApproveDto);

    String proposalnotificationRead(String id);
}