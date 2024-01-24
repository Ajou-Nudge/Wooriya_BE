package com.nudge.wooriya.controller;

import com.nudge.wooriya.service.MetadataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name="AWS S3", description = "AWS S3 관련 API")
@RestController
public class S3Controller {
    private final MetadataService metadataService;

    public S3Controller(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @PostMapping("/img/upload")
    public String upload(@RequestParam("s3Url") String s3Url) {
        return metadataService.postUpload(s3Url);
    }

    @PostMapping("/sign/upload")
    public String signUpload(@RequestParam("s3Url") String s3Url) {
        return metadataService.signUpload(s3Url);
    }

    @PostMapping(value="/img/delete")
    public String postDelete(@RequestParam("s3Url") String s3Url) {
        metadataService.postDelete(s3Url);
        return "삭제 완료";
    }

    @PostMapping(value="/sign/delete")
    public String signDelete(@RequestParam("s3Url") String s3Url) {
        metadataService.signDelete(s3Url);
        return "삭제 완료";
    }
}
