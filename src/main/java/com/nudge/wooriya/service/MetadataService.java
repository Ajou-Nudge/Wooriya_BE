package com.nudge.wooriya.service;

import com.nudge.wooriya.data.entity.PostImageMeta;

import java.util.List;

public interface MetadataService {

    String postUpload(String s3Url);

    void postDelete(String s3Url);

    List<PostImageMeta> list();

    String signUpload(String s3Url);

    void signDelete(String s3Url);
}
