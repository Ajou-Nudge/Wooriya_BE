package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.GroupPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPostRepository extends JpaRepository<GroupPost,Long> {
}