package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Collaboration.ProposalRequest;
import com.nudge.wooriya.common.enums.ProposalStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProposalRequestRepository extends MongoRepository<ProposalRequest, String> {
    List<ProposalRequest> findByProposalStatus(ProposalStatus proposalStatus);
    List<ProposalRequest> findByEmail(String email);
}
