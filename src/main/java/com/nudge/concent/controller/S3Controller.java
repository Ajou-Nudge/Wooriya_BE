package com.nudge.concent.controller;

import ch.qos.logback.core.model.Model;
import com.amazonaws.services.s3.model.S3Object;
import com.nudge.concent.service.MetadataService;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/upload")
        public String upload(@RequestParam("file") MultipartFile file) throws IOException {
            String path = metadataService.upload(file);
            return path;
        }

        @GetMapping(value="/download/{path}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
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
