package com.nudge.wooriya.data.dto.collaboration;

import com.nudge.wooriya.enums.CollaborationPolicy;
import com.nudge.wooriya.enums.ProposalStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class ProposalRequestNotificationDto {
    private Set<CollaborationPolicy> desiredCollaborationType;
    private String name;
    private String dateTime;
    private String message;
    private String cafeId;
    private String email;
}
