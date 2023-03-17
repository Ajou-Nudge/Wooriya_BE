package com.nudge.wooriya.data.dao;

import com.nudge.wooriya.data.entity.GroupPost;

import java.util.List;

public interface GroupPostDAO {
    List<GroupPost> selectAllPost();
    GroupPost selectPost(Long id);
    Long insertPost(GroupPost groupPost);
}
