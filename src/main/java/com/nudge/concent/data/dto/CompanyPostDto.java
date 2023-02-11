package com.nudge.concent.data.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

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