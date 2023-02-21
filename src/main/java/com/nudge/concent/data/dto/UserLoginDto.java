package com.nudge.concent.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLoginDto {
    private String memberId;
    private String password;
}
