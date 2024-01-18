package com.nudge.wooriya.data.dto;

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
