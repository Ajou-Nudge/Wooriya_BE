package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class Proposal {
    @Id
    private Long id;

    private String companyEmail;

    private Long postId;

    private String message;

    private Boolean isApproved;

    private String organizationEmail;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}