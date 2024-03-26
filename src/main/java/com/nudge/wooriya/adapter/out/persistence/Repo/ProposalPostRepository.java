package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.ProposalPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalPostRepository extends MongoRepository<ProposalPost,Long> {
    Optional<List<ProposalPost>> findByAuthor(String author);
}
