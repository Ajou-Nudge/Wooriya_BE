package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class EmailConfirm {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String confirmCode;

    @Column(nullable = false)
    private Boolean isVerify;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
