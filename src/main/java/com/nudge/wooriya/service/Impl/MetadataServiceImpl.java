package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.PostImageMeta;
import com.nudge.wooriya.data.entity.SignImageMeta;
import com.nudge.wooriya.data.repository.PostImageMetaRepository;
import com.nudge.wooriya.data.repository.SignImageMetaRepository;
import com.nudge.wooriya.service.MetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MetadataServiceImpl implements MetadataService {
    @Autowired
    private PostImageMetaRepository postImageMetaRepository;

    @Autowired
    private SignImageMetaRepository signImageMetaRepository;

    @Value("${aws.s3.postbucket.name}")
    private String postBucketName;

    @Value("${aws.s3.signbucket.name}")
    private String signBucketName;

    @Override
    public String postUpload(String s3Url) {
        Date timestamp = new Date();
        UserInfoDto userInfoDto = SecurityUtil.getCurrentMemberId();
        postImageMetaRepository.save(new PostImageMeta(s3Url, timestamp, userInfoDto.getEmail()));
        return s3Url;
    }
    @Override
    public void postDelete(String s3Url) {
        PostImageMeta postImageMeta = postImageMetaRepository.findByS3Url(s3Url);
        postImageMetaRepository.delete(postImageMeta);
    }

    @Override
    public List<PostImageMeta> list() {
        List<PostImageMeta> metas = new ArrayList<>();
        postImageMetaRepository.findAll().forEach(metas::add);
        return metas;
    }

    @Override
    public String signUpload(String s3Url) {
        Date timestamp = new Date();
        UserInfoDto userInfoDto = SecurityUtil.getCurrentMemberId();
        signImageMetaRepository.save(new SignImageMeta(s3Url, timestamp, userInfoDto.getEmail()));
        return s3Url;
    }

    @Override
    public void signDelete(String s3Url) {
        SignImageMeta signImageMeta = signImageMetaRepository.findByS3Url(s3Url);
        signImageMetaRepository.delete(signImageMeta);
    }
}
