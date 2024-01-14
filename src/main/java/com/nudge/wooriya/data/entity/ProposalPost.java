package com.nudge.wooriya.data.entity;

import com.nudge.wooriya.enums.AffiliateForm;
import com.nudge.wooriya.enums.AffiliateKind;
import com.nudge.wooriya.enums.AffiliatePeriod;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class ProposalPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @ElementCollection(targetClass = AffiliateKind.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "post_affiliate_kind", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<AffiliateKind> affiliateKinds;

    @Column(nullable = false)
    private AffiliateForm affiliateForm;

    @Column(nullable = false)
    private AffiliatePeriod affiliatePeriod;

    @Column(nullable = false)
    private String recruitmentPeriod;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_pr_link", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<String> prLinks;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_photos", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<String> photos;

    @Column(length = 1000)
    private String detail;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}