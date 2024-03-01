package com.nudge.wooriya.data.entity.Collaboration;

import com.nudge.wooriya.enums.CollaborationPolicy;
import com.nudge.wooriya.enums.ProposalStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@Getter
@Setter
public class ProposalRequest {
    @Id
    private String id;

    private Set<CollaborationPolicy> desiredCollaborationType;
    private String name;
    private String dateTime;
    private String message;
    private String cafeId;
    private ProposalStatus proposalStatus; // PENDING, READ, NOTREAD
    private String email;
}
