package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Collaboration.ProposalRequest;
import com.nudge.wooriya.enums.ProposalStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProposalRequestRepository extends MongoRepository<ProposalRequest, String> {
    List<ProposalRequest> findByProposalStatus(ProposalStatus proposalStatus);
    List<ProposalRequest> findByEmail(String email);
}
