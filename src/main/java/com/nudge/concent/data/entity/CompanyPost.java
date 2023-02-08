package com.nudge.concent.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.LobTypeMappings;

import java.sql.Blob;
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
    private String companyName;
    @Column(nullable = false)
    private String coType;
    @Column(nullable = false)
    private Integer coSize;
    @Lob
    @Column(length = 3000)
    private Blob img;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}