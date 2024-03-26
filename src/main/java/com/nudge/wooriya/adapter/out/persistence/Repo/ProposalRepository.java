package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends MongoRepository<Proposal,Long> {
    List<Proposal> findAllByCompanyEmail(String companyEmail);

    List<Proposal> findAllByOrganizationEmail(String organizationEmail);
}
