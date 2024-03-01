package com.nudge.wooriya.data.dto.collaboration;

import com.nudge.wooriya.enums.CafeAtmosphere;
import com.nudge.wooriya.enums.CollaborationPolicy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class CollaborationPreviewDto {
    private String proposalTitle;
    private String detailedContent;
    private CafeAtmosphere desiredMood;
    private String date;
    private CollaborationPolicy desiredCollaboration;
    private Integer desiredSize;
    private Integer likes;
}