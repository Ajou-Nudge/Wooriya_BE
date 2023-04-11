package com.nudge.wooriya.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "CompanyPost")
@Getter
@Setter
public class CompanyPost {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String authorId;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String coType;
    @Column(nullable = false)
    private Integer coSize;
    @Column(length = 1000)
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}