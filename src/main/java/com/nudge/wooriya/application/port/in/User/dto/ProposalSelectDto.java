package com.nudge.wooriya.application.port.in.User.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProposalSelectDto {
    private Long proposalId;
    private Boolean select;
}
