package com.nudge.wooriya.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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
