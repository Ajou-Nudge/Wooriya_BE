package com.nudge.wooriya.application.port.in.Collabo.dto;

import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class CollaborationProposalShow {
    private String author;
    private String email;
    private String proposalTitle;
    private String detailedContent;
    private CafeAtmosphere desiredMood;
    private String date;
    private CollaborationPolicy desiredCollaboration;
    private Integer desiredSize;
    private Integer likes;
    private String recruitmentDeadline;
    private List<String> relatedPhotos;
    private List<String> files;
}
