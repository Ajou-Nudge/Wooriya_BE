package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.dao.PostDAO;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.PostImage;
import com.nudge.concent.data.repository.CompanyPostRepository;
import com.nudge.concent.data.repository.PostImageRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDAOImpl implements PostDAO {
    private final PostImageRepository postImageRepository;

    public PostDAOImpl(PostImageRepository postImageRepository) {
        this.postImageRepository = postImageRepository;
    }

    @Override
    public String insertImage(PostImage postImage) {
        String urlImage = postImageRepository.save(postImage).getAddress();
        return urlImage;
    }
}
