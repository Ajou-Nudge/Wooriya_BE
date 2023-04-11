package com.nudge.wooriya.data.dao;

import com.nudge.wooriya.data.entity.CompanyPost;

import java.util.List;

public interface CompanyPostDAO {
    List<CompanyPost> selectAllPost();

    CompanyPost selectPost(Long id);

    Long insertPost(CompanyPost companyPost);

    Long deletePost(Long id);

    Long updatePost(CompanyPost companyPost);
}
