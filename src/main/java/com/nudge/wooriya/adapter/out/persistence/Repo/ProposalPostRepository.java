package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.ProposalPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalPostRepository extends JpaRepository<ProposalPost,Long> {
    Optional<List<ProposalPost>> findByAuthor(String author);
}
