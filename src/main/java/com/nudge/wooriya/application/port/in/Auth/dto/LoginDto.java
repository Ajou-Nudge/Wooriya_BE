package com.nudge.wooriya.application.port.in.Auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {
    private String email;
    private String password;
    private Boolean isCompany;
}
