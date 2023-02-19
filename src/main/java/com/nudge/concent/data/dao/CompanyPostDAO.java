package com.nudge.concent.data.dao;

import com.nudge.concent.data.entity.CompanyPost;

import java.sql.Blob;
import java.util.List;

public interface CompanyPostDAO {
    List<CompanyPost> selectAllPost();

    CompanyPost selectPost(Long id);

    Long insertPost(CompanyPost companyPost);
}
