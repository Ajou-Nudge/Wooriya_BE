package com.nudge.concent.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.LobTypeMappings;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "CompanyImage")
@Getter
@Setter
public class CompanyImage {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(length = 20971520)
    private Blob img;
}