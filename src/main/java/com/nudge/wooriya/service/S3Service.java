package com.nudge.wooriya.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface S3Service {
    public PutObjectResult upload(
            String path,
            String fileName,
            Optional<Map<String, String>> optionalMetaData,
            InputStream inputStream
    );

    void postDelete(String path, String fileName);

    void signDelete(String path, String fileName);

    public S3Object download(String path, String fileName);
}
