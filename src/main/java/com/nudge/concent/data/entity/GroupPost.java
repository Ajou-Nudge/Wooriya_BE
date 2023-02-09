package com.nudge.concent.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "GroupPost")
@Getter
@Setter
public class GroupPost {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String groupName;
    @Column(nullable = false)
    private String coType;
    @Column(nullable = false)
    private Integer coSize;
    @Lob
    @Column(length = 5000)
    private Blob img;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}