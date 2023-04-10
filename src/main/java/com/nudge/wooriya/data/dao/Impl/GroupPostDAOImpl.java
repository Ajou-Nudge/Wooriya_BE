package com.nudge.wooriya.data.dao.Impl;

import com.nudge.wooriya.data.dao.GroupPostDAO;
import com.nudge.wooriya.data.entity.GroupPost;
import com.nudge.wooriya.data.repository.GroupPostRepository;
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
    public GroupPost selectPost(Long id) {
        GroupPost groupPost = groupPostRepository.getReferenceById(id);
        return groupPost;
    }

    @Override
    public Long insertPost(GroupPost groupPost) {
        Long postId = groupPostRepository.save(groupPost).getId();
        return postId;
    }

    @Override
    public void deletePost(Long id) {
        GroupPost groupPost = selectPost(id);
        groupPostRepository.delete(groupPost);
    }

    @Override
    public Long updatePost(GroupPost groupPost) {
        Long postId = groupPostRepository.save(groupPost).getId();
        return postId;
    }
}
