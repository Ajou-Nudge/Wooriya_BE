package com.nudge.wooriya.adapter.out.persistence.MongoEntity.Collabo;

import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
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
    private String id;

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
