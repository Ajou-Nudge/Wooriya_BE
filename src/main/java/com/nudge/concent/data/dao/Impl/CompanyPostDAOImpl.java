package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.PostImage;
import com.nudge.concent.data.repository.PostImageRepository;
import com.nudge.concent.data.repository.CompanyPostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyPostDAOImpl implements CompanyPostDAO {
    private final CompanyPostRepository companyPostRepository;
    private final PostImageRepository postImageRepository;

    public CompanyPostDAOImpl(CompanyPostRepository companyPostRepository, PostImageRepository postImageRepository) {
        this.companyPostRepository = companyPostRepository;
        this.postImageRepository = postImageRepository;
    }

    public String insertImage(PostImage postImage) {
        String urlImage = postImageRepository.save(postImage).getAddress();
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
