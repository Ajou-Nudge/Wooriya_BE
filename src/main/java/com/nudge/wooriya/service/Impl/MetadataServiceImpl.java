package com.nudge.wooriya.service.Impl;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.data.entity.PostImageMeta;
import com.nudge.wooriya.data.entity.SignImageMeta;
import com.nudge.wooriya.data.repository.PostImageMetaRepository;
import com.nudge.wooriya.data.repository.SignImageMetaRepository;
import com.nudge.wooriya.service.MetadataService;
import com.nudge.wooriya.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class MetadataServiceImpl implements MetadataService {
    @Autowired
    private S3Service amazonS3Service;

    @Autowired
    private PostImageMetaRepository postImageMetaRepository;

    @Autowired
    private SignImageMetaRepository signImageMetaRepository;

    @Value("${aws.s3.postbucket.name}")
    private String postBucketName;

    @Value("${aws.s3.signbucket.name}")
    private String signBucketName;

    @Override
    public String postUpload(MultipartFile file) throws IOException {
        final String pathHeader = postBucketName + "/";

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", postBucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        PutObjectResult putObjectResult = amazonS3Service.upload(path, fileName, Optional.of(metadata), file.getInputStream());

        postImageMetaRepository.save(new PostImageMeta(fileName, path, putObjectResult.getMetadata().getVersionId()));
        return path.substring(pathHeader.length());
    }

    @Override
    public S3Object postDownload(String path) {
        final String pathHeader = postBucketName + "/";
        PostImageMeta postImageMeta = postImageMetaRepository.findByFilePath(pathHeader + path);
        return amazonS3Service.download(postImageMeta.getFilePath(),postImageMeta.getFileName());
    }

    @Override
    public void postDelete(String path) {
        final String pathHeader = postBucketName + "/";
        PostImageMeta postImageMeta = postImageMetaRepository.findByFilePath(pathHeader + path);
        postImageMetaRepository.delete(postImageMeta);
        amazonS3Service.postDelete(postImageMeta.getFilePath(),postImageMeta.getFileName());
    }

    @Override
    public List<PostImageMeta> list() {
        List<PostImageMeta> metas = new ArrayList<>();
        postImageMetaRepository.findAll().forEach(metas::add);
        return metas;
    }

    @Override
    public String signUpload(MultipartFile file) throws IOException {
        final String pathHeader = signBucketName + "/";

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", signBucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        PutObjectResult putObjectResult = amazonS3Service.upload(path, fileName, Optional.of(metadata), file.getInputStream());

        signImageMetaRepository.save(new SignImageMeta(fileName, path, putObjectResult.getMetadata().getVersionId()));
        return path.substring(pathHeader.length());
    }

    @Override
    public S3Object signDownload(String path) {
        final String pathHeader = signBucketName + "/";
        SignImageMeta signImageMeta = signImageMetaRepository.findByFilePath(pathHeader + path);
        return amazonS3Service.download(signImageMeta.getFilePath(), signImageMeta.getFileName());
    }

    @Override
    public void signDelete(String path) {
        final String pathHeader = signBucketName + "/";
        SignImageMeta signImageMeta = signImageMetaRepository.findByFilePath(pathHeader + path);
        amazonS3Service.signDelete(signImageMeta.getFilePath(), signImageMeta.getFileName());
        signImageMetaRepository.delete(signImageMeta);
    }

    @Override
    public SignImageMeta signMeta(String path) {
        final String pathHeader = signBucketName + "/";
        return signImageMetaRepository.findByFilePath(pathHeader + path);
    }
}
