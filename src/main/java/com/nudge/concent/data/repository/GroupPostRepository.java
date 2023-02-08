package com.nudge.concent.data.repository;

import com.nudge.concent.data.entity.GroupPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPostRepository extends JpaRepository<GroupPost,Long> {
}
