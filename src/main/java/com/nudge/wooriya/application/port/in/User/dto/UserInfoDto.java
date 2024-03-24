package com.nudge.wooriya.application.port.in.User.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserInfoDto {
    private String email;
    private String memberRole;
}
