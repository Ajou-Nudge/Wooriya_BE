package com.nudge.concent.data.dao;

import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.GroupPost;

import java.util.List;

public interface GroupPostDAO {
    List<GroupPost> selectAllPost();
    GroupPost selectPost(Long id);
    Long insertPost(GroupPost groupPost);
}
