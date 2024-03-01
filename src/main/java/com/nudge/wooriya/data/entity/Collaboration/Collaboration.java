package com.nudge.wooriya.data.entity.Collaboration;

import com.nudge.wooriya.enums.CafeAtmosphere;
import com.nudge.wooriya.enums.CollaborationPolicy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Getter
@Setter
public class Collaboration {
    @Id
    private String collaborationId;

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
