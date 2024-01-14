package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Company;
import com.nudge.wooriya.data.entity.ProposalPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalPostRepository extends JpaRepository<ProposalPost,Long> {
    Optional<ProposalPost> findById(Long id);
}
