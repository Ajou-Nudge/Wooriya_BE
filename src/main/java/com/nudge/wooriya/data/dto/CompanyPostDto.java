package com.nudge.wooriya.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CompanyPostDto {
    private Long id;
    private String title;
    private String companyName;
    private String coType;
    private int coSize;
    private String body;
}