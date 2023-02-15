package com.nudge.concent.data.dao;

import com.nudge.concent.data.entity.PostImage;

import java.sql.SQLException;

public interface PostDAO {
    String insertImage(PostImage postImage);

    String selectImage(String address) throws SQLException;
}
