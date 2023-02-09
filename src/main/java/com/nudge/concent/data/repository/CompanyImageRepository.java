package com.nudge.concent.data.repository;

import com.nudge.concent.data.entity.CompanyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyImageRepository extends JpaRepository<CompanyImage,Long> {
}
