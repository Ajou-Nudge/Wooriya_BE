package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.PostImageMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageMetaRepository extends JpaRepository<PostImageMeta, Long> {
    PostImageMeta findByS3Url(String s3Url);
}
