package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.repository.CompanyPostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyPostDAOImpl implements CompanyPostDAO {
    private final CompanyPostRepository companyPostRepository;

    public CompanyPostDAOImpl(CompanyPostRepository companyPostRepository) {
        this.companyPostRepository = companyPostRepository;
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
