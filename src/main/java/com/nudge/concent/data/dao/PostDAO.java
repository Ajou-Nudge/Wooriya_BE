package com.nudge.concent.data.dao;

import com.nudge.concent.data.entity.PostImage;

import java.sql.Blob;
import java.sql.SQLException;

public interface PostDAO {
    String insertImage(PostImage postImage);

    Blob selectImage(String address) throws SQLException;
}
