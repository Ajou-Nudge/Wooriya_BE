package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal,Long> {
    List<Proposal> findAllByCompanyEmail(String companyEmail);

    List<Proposal> findAllByOrganizationEmail(String organizationEmail);
}
