package com.nudge.wooriya.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.data.entity.SignImageMeta;
import com.nudge.wooriya.service.MetadataService;
import com.nudge.wooriya.service.S3Service;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {
    private final MetadataService metadataService;
    private final S3Service s3Service;

    public S3Controller(MetadataService metadataService, S3Service s3Service) {
        this.metadataService = metadataService;
        this.s3Service = s3Service;
    }

        @PostMapping("/img/upload")
        public String upload(@RequestParam("img") MultipartFile img) throws IOException {
            String path = metadataService.postUpload(img);
            return path;
        }

        @GetMapping(value="/img/{path}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
        @ResponseBody
        public HttpEntity<byte[]> download(@PathVariable String path) throws IOException {
            S3Object s3Object = metadataService.postDownload(path);
            String contentType = s3Object.getObjectMetadata().getContentType();
            byte[] bytes = s3Object.getObjectContent().readAllBytes();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(contentType));
            header.setContentLength(bytes.length);

            return new ResponseEntity<>(bytes, HttpStatus.OK);
        }

    @PostMapping("/sign/upload")
    public String signUpload(@RequestParam("img") MultipartFile img) throws IOException {
        String path = metadataService.signUpload(img);
        return path;
    }

    @GetMapping(value="/sign/{path}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public HttpEntity<byte[]> signDownload(@PathVariable String path) throws IOException {
        S3Object s3Object = metadataService.signDownload(path);
        String contentType = s3Object.getObjectMetadata().getContentType();
        byte[] bytes = s3Object.getObjectContent().readAllBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(contentType));
        header.setContentLength(bytes.length);

        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @GetMapping(value="/sign/delete/{path}")
    public String signDelete(@PathVariable String path) {
        SignImageMeta signImageMeta = metadataService.signMeta(path);
        s3Service.signDelete(signImageMeta.getFilePath(), signImageMeta.getFileName());
        metadataService.signDelete(path);
        return "삭제 완료";
    }
}
