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
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyEmail;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = true)
    private String message;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}