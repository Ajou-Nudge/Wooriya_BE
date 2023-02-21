package com.nudge.concent.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserJoinDto {
    private String memberId;
    private String password;
    private String role;
}
