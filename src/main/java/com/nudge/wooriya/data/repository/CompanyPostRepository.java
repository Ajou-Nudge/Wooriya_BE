package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.CompanyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPostRepository extends JpaRepository<CompanyPost,Long> {
}
