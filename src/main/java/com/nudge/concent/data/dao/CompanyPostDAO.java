package com.nudge.concent.data.dao;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.entity.CompanyPost;

import java.util.List;

public interface CompanyPostDAO {
    List<CompanyPost> selectAllPost();

    Long insertPost(CompanyPost companyPost);
}
