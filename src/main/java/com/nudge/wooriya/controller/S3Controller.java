package com.nudge.wooriya.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.service.MetadataService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {
    private MetadataService metadataService;

    public S3Controller(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

        @PostMapping("/imgupload")
        public String upload(@RequestParam("img") MultipartFile img) throws IOException {
            String path = metadataService.upload(img);
            return path;
        }

        @GetMapping(value="/image/{path}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
        @ResponseBody
        public HttpEntity<byte[]> download(@PathVariable String path) throws IOException {
            S3Object s3Object = metadataService.download(path);
            String contentType = s3Object.getObjectMetadata().getContentType();
            byte[] bytes = s3Object.getObjectContent().readAllBytes();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(contentType));
            header.setContentLength(bytes.length);

            return new ResponseEntity<>(bytes, HttpStatus.OK);
        }
}
