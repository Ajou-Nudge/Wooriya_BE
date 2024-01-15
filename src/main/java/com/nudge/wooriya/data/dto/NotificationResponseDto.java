package com.nudge.wooriya.data.dto;

import com.nudge.wooriya.data.entity.Proposal;
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
