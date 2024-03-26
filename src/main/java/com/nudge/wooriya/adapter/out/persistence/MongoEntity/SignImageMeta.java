package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@Document
@Getter
@Setter
public class SignImageMeta {
    @Id
    private int id;

    private Long memberEmail;

    private String s3Url;

    private Date timestamp;

    private String author;

    public SignImageMeta(String s3Url, Date timestamp, String author) {
        this.s3Url = s3Url;
        this.timestamp = timestamp;
        this.author = author;
    }
}