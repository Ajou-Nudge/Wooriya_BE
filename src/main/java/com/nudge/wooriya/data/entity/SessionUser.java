package com.nudge.wooriya.data.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(Organization organization) {
        this.name = organization.getOrganizationName();
        this.email = organization.getEmail();
//        this.picture = user.getPicture();
    }
}