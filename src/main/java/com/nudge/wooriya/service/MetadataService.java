package com.nudge.wooriya.service;

import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.data.entity.PostImageMeta;
import com.nudge.wooriya.data.entity.SignImageMeta;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MetadataService {

    String postUpload(String s3Url);

    void postDelete(String s3Url);

    List<PostImageMeta> list();

    String signUpload(String s3Url);

    void signDelete(String s3Url);
}
