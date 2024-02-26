package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.collaboration.CollaborationPreviewDto;
import com.nudge.wooriya.data.dto.collaboration.CollaborationProposalDto;
import com.nudge.wooriya.data.entity.Collaboration;

import java.util.List;


public interface CollaborationSerivce {
    Collaboration postCollaborationPosting(Collaboration collaboration) throws Exception; // 협업 제안 글 쓰

    List<CollaborationPreviewDto> getCollaborationPreview(); // 협업 제안 미리보기

    CollaborationProposalDto getCollaborationProposalById(String id); // 협업 제안 글 보여주기기
}