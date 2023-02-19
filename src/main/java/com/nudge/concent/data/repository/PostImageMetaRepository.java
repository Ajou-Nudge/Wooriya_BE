package com.nudge.concent.data.repository;

import com.nudge.concent.data.entity.PostImageMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageMetaRepository extends CrudRepository<PostImageMeta, Integer> {
    PostImageMeta findByFilePath(String filePath);
}
