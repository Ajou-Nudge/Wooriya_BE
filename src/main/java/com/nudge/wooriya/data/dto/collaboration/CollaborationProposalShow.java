package com.nudge.wooriya.data.dto.collaboration;

import com.nudge.wooriya.enums.CafeAtmosphere;
import com.nudge.wooriya.enums.CollaborationPolicy;
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
