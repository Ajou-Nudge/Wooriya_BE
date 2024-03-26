package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class Notification {
    @Id
    private Long id;


    private Long proposalId;

    private String receiver;

    private String message;

    private Boolean isRead;
}
