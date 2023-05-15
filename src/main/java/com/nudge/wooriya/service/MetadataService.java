package com.nudge.wooriya.service;

import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.data.entity.PostImageMeta;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MetadataService {
    public String upload(MultipartFile file) throws IOException;
    public S3Object download(String path);

    void delete(String path);

    public List<PostImageMeta> list();
}
