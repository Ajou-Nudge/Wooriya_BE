package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.PostImageMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageMetaRepository extends CrudRepository<PostImageMeta, Integer> {
    PostImageMeta findByFilePath(String filePath);
}
