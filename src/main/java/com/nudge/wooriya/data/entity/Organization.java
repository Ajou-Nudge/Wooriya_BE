package com.nudge.wooriya.data.entity;

import com.nudge.wooriya.enums.CompanyHistory;
import com.nudge.wooriya.enums.CompanyKind;
import com.nudge.wooriya.enums.OrganizationHistory;
import com.nudge.wooriya.enums.OrganizationKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String organizationName;

    @Column(nullable = false)
    private String representativeName;

    @Column(nullable = false)
    private String representativeNum;

    @Column(nullable = false)
    private String organizationEmail;

    @Column(nullable = true)
    private boolean isVerify;

    @Column(nullable = false)
    private OrganizationKind kind;

    @Column(nullable = false)
    private OrganizationHistory history;

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

}
