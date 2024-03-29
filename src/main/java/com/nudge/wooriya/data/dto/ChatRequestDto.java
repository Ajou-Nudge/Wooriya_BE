package com.nudge.wooriya.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChatRequestDto {
    private String message;
    private String sessionId;
    private String receiverEmail;
}
