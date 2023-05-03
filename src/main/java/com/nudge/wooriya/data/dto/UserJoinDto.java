package com.nudge.wooriya.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserJoinDto {
    private String email;
    private String password;
    private String role;
    private String userName;
    private String userNum;
}
