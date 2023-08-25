package com.nudge.wooriya.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "PostImage_META")
@Getter
@Setter
public class PostImageMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "S3_URL")
    private String s3Url;

    @Column(name = "POST_NUM")
    private Long postNum;

    @Column(name = "TIMESTAMP")
    private Date timestamp;

    @Column(name = "AUTHOR")
    private String author;

    public PostImageMeta(String s3Url, Date timestamp, String author) {
        this.s3Url = s3Url;
        this.timestamp = timestamp;
        this.author = author;
    }
}