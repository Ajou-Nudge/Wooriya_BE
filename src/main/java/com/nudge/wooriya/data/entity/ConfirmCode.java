package com.nudge.wooriya.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="ConfirmCode")
@Getter
@Setter
public class ConfirmCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long tokenId;

    @Column
    private String confirmCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column
    private String email;

    public ConfirmCode(String email) {
        this.email = email;
        createdDate = new Date();
        confirmCode = UUID.randomUUID().toString();
    }

    public ConfirmCode() {

    }
}