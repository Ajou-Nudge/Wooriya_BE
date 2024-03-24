package com.nudge.wooriya.application.port.in.Collabo.dto;

import com.nudge.wooriya.common.enums.CollaborationPolicy;
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
