package com.nudge.wooriya.service.Impl;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.nudge.wooriya.data.entity.PostImageMeta;
import com.nudge.wooriya.data.repository.PostImageMetaRepository;
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

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) throws IOException {
        final String pathHeader = bucketName + "/";

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        PutObjectResult putObjectResult = amazonS3Service.upload(path, fileName, Optional.of(metadata), file.getInputStream());

        postImageMetaRepository.save(new PostImageMeta(fileName, path, putObjectResult.getMetadata().getVersionId()));
        return path.substring(pathHeader.length());
    }

    @Override
    public S3Object download(String path) {
        final String pathHeader = bucketName + "/";
        PostImageMeta postImageMeta = postImageMetaRepository.findByFilePath(pathHeader + path);
        return amazonS3Service.download(postImageMeta.getFilePath(),postImageMeta.getFileName());
    }

    @Override
    public void delete(String path) {
        final String pathHeader = bucketName + "/";
        PostImageMeta postImageMeta = postImageMetaRepository.findByFilePath(pathHeader + path);
        amazonS3Service.delete(postImageMeta.getFilePath(),postImageMeta.getFileName());
    }

    @Override
    public List<PostImageMeta> list() {
        List<PostImageMeta> metas = new ArrayList<>();
        postImageMetaRepository.findAll().forEach(metas::add);
        return metas;
    }
}
