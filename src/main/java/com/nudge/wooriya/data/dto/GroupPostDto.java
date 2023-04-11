package com.nudge.wooriya.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupPostDto {
    private Long id;
    private String title;
    private String authorId;
    private String groupName;
    private String coType;
    private int coSize;
    private String body;
}