package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.enums.AffiliateForm;
import com.nudge.wooriya.enums.AffiliateKind;
import com.nudge.wooriya.enums.AffiliatePeriod;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProposalRequestDto {
    private Long postId;
    private String message;
}