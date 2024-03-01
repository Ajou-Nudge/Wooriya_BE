package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.collaboration.*;
import com.nudge.wooriya.data.entity.Collaboration.Collaboration;
import com.nudge.wooriya.data.entity.Collaboration.ProposalRequest;

import java.util.List;


public interface CollaborationSerivce {
    Collaboration postCollaborationPosting(Collaboration collaboration) throws Exception; // 협업 제안 글 쓰

    List<CollaborationPreviewDto> getCollaborationPreview(); // 협업 제안 미리보기

    CollaborationProposalDto getCollaborationProposalById(String id); // 협업 제안 글 보여주기기

    String postProposalRequest(ProposalRequestNotificationDto proposalRequest) throws Exception;

    List<ProposalNotificationDto> postProposalnotification(String email) throws Exception;

    List<ProposalRequest> getAllProposalRequests();

    String postApproveProposalRequest(ProposalApproveDto proposalApproveDto);

    String proposalnotificationRead(String id);
}