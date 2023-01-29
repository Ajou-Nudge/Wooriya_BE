package com.nudge.concent.data.repository;

import com.nudge.concent.data.entity.CompanyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPostRepository extends JpaRepository<CompanyPost,Long> {
}
