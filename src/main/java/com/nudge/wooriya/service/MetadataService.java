package com.nudge.wooriya.service;

import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.data.entity.PostImageMeta;
import com.nudge.wooriya.data.entity.SignImageMeta;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MetadataService {

    String postUpload(MultipartFile file) throws IOException;

    S3Object postDownload(String path);

    void postDelete(String path);

    public List<PostImageMeta> list();

    String signUpload(MultipartFile file) throws IOException;

    S3Object signDownload(String path);

    void signDelete(String path);

    SignImageMeta signMeta(String path);
}
