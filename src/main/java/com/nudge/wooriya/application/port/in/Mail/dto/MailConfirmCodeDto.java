package com.nudge.wooriya.application.port.in.Mail.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MailConfirmCodeDto {
    private String email;
    private String confirmCode;
}