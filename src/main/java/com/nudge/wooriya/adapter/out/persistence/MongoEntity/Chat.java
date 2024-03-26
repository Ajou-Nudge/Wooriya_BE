package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class Chat {
    @Id
    private String id;
    private String sessionId;
    private String senderEmail;
    private String message;
    @CreatedDate
    private Date timestamp;

}
