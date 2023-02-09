package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.entity.CompanyImage;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.repository.CompanyImageRepository;
import com.nudge.concent.data.repository.CompanyPostRepository;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.util.List;

@Component
public class CompanyPostDAOImpl implements CompanyPostDAO {
    private final CompanyPostRepository companyPostRepository;
    private final CompanyImageRepository companyImageRepository;

    public CompanyPostDAOImpl(CompanyPostRepository companyPostRepository, CompanyImageRepository companyImageRepository) {
        this.companyPostRepository = companyPostRepository;
        this.companyImageRepository = companyImageRepository;
    }

    public String insertImage(CompanyImage companyImage) {
        String urlImage = companyImageRepository.save(companyImage).getId().toString();
        return urlImage;
    }

    @Override
    public List<CompanyPost> selectAllPost() {
        List<CompanyPost> companyPosts = companyPostRepository.findAll();
        return companyPosts;
    }

    @Override
    public Long insertPost(CompanyPost companyPost) {
        Long postId = companyPostRepository.save(companyPost).getId();
        return postId;
    }
}
