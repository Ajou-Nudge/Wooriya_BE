package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.data.dto.collaboration.*;
import com.nudge.wooriya.data.entity.Collaboration.Collaboration;
import com.nudge.wooriya.data.entity.Collaboration.ProposalRequest;
import com.nudge.wooriya.data.repository.CollaborationRepository;
import com.nudge.wooriya.data.repository.ProposalRequestRepository;
import com.nudge.wooriya.enums.ProposalStatus;
import com.nudge.wooriya.service.CollaborationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollaborationServiceImpl implements CollaborationSerivce {
    private final CollaborationRepository collaborationRepository;
    private final ProposalRequestRepository proposalRequestRepository;

    @Autowired
    public CollaborationServiceImpl(CollaborationRepository collaborationRepository, ProposalRequestRepository proposalRequestRepository){
        this.collaborationRepository = collaborationRepository;
        this.proposalRequestRepository = proposalRequestRepository;
    }


    @Override
    public Collaboration postCollaborationPosting(Collaboration collaboration) throws Exception{
        return collaborationRepository.save(collaboration);
    }

    @Override
    public List<CollaborationPreviewDto> getCollaborationPreview(){
        // 예시 데이터 조회 로직
        List<Collaboration> collaborations = collaborationRepository.findAll();

        // 변환 로직
        return collaborations.stream()
                .map(collaboration -> {
                    CollaborationPreviewDto dto = new CollaborationPreviewDto();
                    dto.setProposalTitle(collaboration.getProposalTitle());
                    dto.setDetailedContent(collaboration.getDetailedContent());
                    dto.setDesiredMood(collaboration.getDesiredMood());
                    dto.setDate(collaboration.getDate());
                    dto.setDesiredCollaboration(collaboration.getDesiredCollaboration());
                    dto.setDesiredSize(collaboration.getDesiredSize());
                    dto.setLikes(collaboration.getLikes());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CollaborationProposalDto getCollaborationProposalById(String id){
        // ID로 데이터 조회
        Collaboration collaboration = collaborationRepository.findById(id).orElseThrow(() -> new RuntimeException("Collaboration not found"));
        // 변환 로직
        CollaborationProposalDto dto = new CollaborationProposalDto();
        dto.setProposalTitle(collaboration.getProposalTitle());
        dto.setDetailedContent(collaboration.getDetailedContent());
        dto.setDesiredMood(collaboration.getDesiredMood());
        dto.setDate(collaboration.getDate());
        dto.setDesiredCollaboration(collaboration.getDesiredCollaboration());
        dto.setDesiredSize(collaboration.getDesiredSize());
        dto.setLikes(collaboration.getLikes());
        dto.setRecruitmentDeadline(collaboration.getRecruitmentDeadline());
        dto.setRelatedPhotos(collaboration.getRelatedPhotos()); // 가정: Collaboration 엔티티에 해당 필드가 존재한다고 가정
        dto.setFiles(collaboration.getFiles()); // 가정: Collaboration 엔티티에 해당 필드가 존재한다고 가정
        return dto;
    }

    public List<ProposalNotificationDto> postProposalnotification(String email) {
        List<ProposalRequest> proposalRequests = proposalRequestRepository.findByEmail(email);

        return proposalRequests.stream()
                .map(this::mapToProposalNotificationDto)
                .collect(Collectors.toList());
    }
    private ProposalNotificationDto mapToProposalNotificationDto(ProposalRequest proposalRequest) {
        ProposalNotificationDto dto = new ProposalNotificationDto();
        dto.setId(proposalRequest.getId());
        dto.setDesiredCollaborationType(proposalRequest.getDesiredCollaborationType());
        dto.setName(proposalRequest.getName());
        dto.setDateTime(proposalRequest.getDateTime());
        dto.setMessage(proposalRequest.getMessage());
        dto.setCafeId(proposalRequest.getCafeId());
        dto.setProposalStatus(proposalRequest.getProposalStatus());
        return dto;
    }

    @Override
    public String postProposalRequest(ProposalRequestNotificationDto proposalRequest) throws Exception {
        ProposalRequest newProposal = new ProposalRequest();
        newProposal.setDesiredCollaborationType(proposalRequest.getDesiredCollaborationType());
        newProposal.setName(proposalRequest.getName());
        newProposal.setDateTime(proposalRequest.getDateTime());
        newProposal.setMessage(proposalRequest.getMessage());
        newProposal.setCafeId(proposalRequest.getCafeId());
        newProposal.setProposalStatus(ProposalStatus.PENDING); // Setting default status to PENDING
        newProposal.setEmail(proposalRequest.getEmail()); // Assuming email is provided in ProposalRequestNotificationDto

        proposalRequestRepository.save(newProposal);
        return "success";
    }
    @Override
    public List<ProposalRequest> getAllProposalRequests() {
        ProposalStatus proposalStatus = ProposalStatus.PENDING; // 승인 받지 못한 것들
        return proposalRequestRepository.findByProposalStatus(proposalStatus);
    }
    public String postApproveProposalRequest(ProposalApproveDto proposalApproveDto) {
        List<String> approvedProposalIds = proposalApproveDto.getApprovedProposalId();

        for (String id : approvedProposalIds) {
            ProposalRequest proposalRequest = proposalRequestRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Proposal request not found with id: " + id));

            proposalRequest.setProposalStatus(ProposalStatus.NOTREAD);
            proposalRequestRepository.save(proposalRequest);
        }

        return "Success";
    }

    @Override
    public String proposalnotificationRead(String id){
        ProposalRequest proposalRequest = proposalRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposal request not found with id: " + id));

        proposalRequest.setProposalStatus(ProposalStatus.READ);
        proposalRequestRepository.save(proposalRequest);

        return "Success";
    }

}
