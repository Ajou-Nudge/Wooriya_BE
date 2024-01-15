package com.nudge.wooriya.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long proposalId;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean isRead;
}
