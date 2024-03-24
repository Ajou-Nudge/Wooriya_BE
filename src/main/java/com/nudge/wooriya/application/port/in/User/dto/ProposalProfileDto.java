package com.nudge.wooriya.application.port.in.User.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProposalProfileDto {
    private Long id;
    private String companyName;
    private Long postId;
    private String message;
    private Boolean isApproved;
    private LocalDateTime updatedAt;
}