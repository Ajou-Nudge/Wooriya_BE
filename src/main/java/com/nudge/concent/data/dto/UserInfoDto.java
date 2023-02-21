package com.nudge.concent.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserInfoDto {
    private String memberId;
    private String memberRole;
}
