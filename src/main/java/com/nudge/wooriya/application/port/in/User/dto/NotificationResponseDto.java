package com.nudge.wooriya.application.port.in.User.dto;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NotificationResponseDto {
    private Long id;
    private String message;
    private Proposal proposal;
}
