package com.nudge.wooriya.data.entity;

import com.nudge.wooriya.enums.CompanyHistory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.nudge.wooriya.enums.CompanyKind;

import java.util.ArrayList;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyNum;

    @Column(nullable = false)
    private String representativeName;

    @Column(nullable = false)
    private String representativeNum;

    @Column(nullable = true)
    private boolean isVerify;

    @Column(nullable = false)
    private CompanyKind kind;

    @Column(nullable = false)
    private CompanyHistory history;

}
