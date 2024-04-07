package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import com.nudge.wooriya.common.enums.AffiliateForm;
import com.nudge.wooriya.common.enums.AffiliateKind;
import com.nudge.wooriya.common.enums.AffiliatePeriod;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class ProposalPost { // 로직 확인, 따로 저장할 필요성이 있는가?
    @Id
    private Long id;

    private String author;
    private String title;


    private Set<AffiliateKind> affiliateKinds;


    private AffiliateForm affiliateForm;


    private AffiliatePeriod affiliatePeriod;

    private String recruitmentPeriod;

    private Set<String> prLinks;


    private Set<String> photos;

    private String detail;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}