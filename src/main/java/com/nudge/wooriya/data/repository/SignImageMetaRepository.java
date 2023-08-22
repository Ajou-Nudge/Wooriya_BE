package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.SignImageMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignImageMetaRepository extends JpaRepository<SignImageMeta, Long> {
    SignImageMeta findByFilePath(String filePath);
}
