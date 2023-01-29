package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.dao.GroupPostDAO;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.GroupPost;
import com.nudge.concent.data.repository.CompanyPostRepository;
import com.nudge.concent.data.repository.GroupPostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupPostDAOImpl implements GroupPostDAO {
    private final GroupPostRepository groupPostRepository;

    public GroupPostDAOImpl(GroupPostRepository groupPostRepository) {
        this.groupPostRepository = groupPostRepository;
    }

    @Override
    public List<GroupPost> selectAllPost() {
        List<GroupPost> groupPosts = groupPostRepository.findAll();
        return groupPosts;
    }

    @Override
    public Long insertPost(GroupPost groupPost) {
        Long postId = groupPostRepository.save(groupPost).getId();
        return postId;
    }
}
