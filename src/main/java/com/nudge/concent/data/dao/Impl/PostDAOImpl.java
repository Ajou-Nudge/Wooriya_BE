package com.nudge.concent.data.dao.Impl;

import com.nudge.concent.data.dao.PostDAO;
import com.nudge.concent.data.entity.PostImage;
import com.nudge.concent.data.repository.PostImageRepository;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public class PostDAOImpl implements PostDAO {
    private final PostImageRepository postImageRepository;

    public PostDAOImpl(PostImageRepository postImageRepository) {
        this.postImageRepository = postImageRepository;
    }

    @Override
    public String insertImage(PostImage postImage) {
        String urlImage = postImageRepository.save(postImage).getAddress();
        return urlImage;
    }

    @Override
    public String selectImage(String address) throws SQLException {
        Blob blob = new SerialBlob(postImageRepository.getByAddress(address).get(0).getImg());
        byte[] base64Image = Base64.getEncoder().encode(blob.getBytes(1, (int)blob.length()));
        String base64Url = new String(base64Image);
        return base64Url;
    }
}
