package com.nudge.wooriya.application.port.in.Chat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ChatResponseDto {
    private String senderEmail;
    private String sessionId;
    private String message;
    private Date timestamp;
}
