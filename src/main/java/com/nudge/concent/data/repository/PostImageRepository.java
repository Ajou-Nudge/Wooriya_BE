package com.nudge.concent.data.repository;

import com.nudge.concent.data.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage,Long> {
    List<PostImage> getByAddress(String address);
}