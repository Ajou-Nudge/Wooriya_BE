package com.nudge.wooriya.application.port.in.Prorposal.dto;

import com.nudge.wooriya.common.enums.AffiliateForm;
import com.nudge.wooriya.common.enums.AffiliateKind;
import com.nudge.wooriya.common.enums.AffiliatePeriod;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProposalPostResponseDto {
    private Long id;
    private String title;
    private String author;
    private Set<AffiliateKind> affiliateKinds;
    private AffiliateForm affiliateForm;
    private AffiliatePeriod affiliatePeriod;
    private String recruitmentPeriod;
    private Set<String> prLinks;
    private Set<String> photos;
    private String detail;
}