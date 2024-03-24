package com.nudge.wooriya.application.port.in.Chat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChatsRequestBySessionIdDto {
    private Long sessionId;
}
