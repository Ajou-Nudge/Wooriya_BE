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
public class EmailConfirm {
    @Id
    private String email;

    private String confirmCode;

    private Boolean isVerify;

    private LocalDateTime timestamp;
}
