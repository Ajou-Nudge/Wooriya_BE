package com.nudge.wooriya.application.port.in.Collabo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class ProposalApproveDto {
    List<String> approvedProposalId;
}
