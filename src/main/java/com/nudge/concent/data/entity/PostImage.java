package com.nudge.concent.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Table(name = "PostImage")
@Getter
@Setter
public class PostImage {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(length = 20971520)
    private Blob img;
    @Column(nullable = false)
    private String address;
}