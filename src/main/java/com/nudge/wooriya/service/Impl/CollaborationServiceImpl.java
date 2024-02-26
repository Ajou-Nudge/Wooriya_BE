package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.data.dto.collaboration.CollaborationPreviewDto;
import com.nudge.wooriya.data.dto.collaboration.CollaborationProposalDto;
import com.nudge.wooriya.data.entity.Collaboration;
import com.nudge.wooriya.data.repository.CollaborationRepository;
import com.nudge.wooriya.service.CollaborationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollaborationServiceImpl implements CollaborationSerivce {
    private final CollaborationRepository collaborationRepository;

    @Autowired
    public  CollaborationServiceImpl(CollaborationRepository collaborationRepository){
        this.collaborationRepository = collaborationRepository;
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


}
