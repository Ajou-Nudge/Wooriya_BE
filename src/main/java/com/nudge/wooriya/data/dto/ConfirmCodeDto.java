package com.nudge.wooriya.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConfirmCodeDto {
    private String email;
    private String confirmCode;
}