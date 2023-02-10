package com.nudge.concent.data.dao;

import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.PostImage;

import java.sql.Blob;
import java.util.List;

public interface CompanyPostDAO {
    List<CompanyPost> selectAllPost();

    Long insertPost(CompanyPost companyPost);

    String insertImage(PostImage postImage);
}
