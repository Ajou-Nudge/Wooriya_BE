package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Proposal;
import com.nudge.wooriya.data.entity.ProposalPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal,Long> {
    List<Proposal> findAllByCompanyEmail(String companyEmail);

    List<Proposal> findAllByOrganizationEmail(String organizationEmail);
}
