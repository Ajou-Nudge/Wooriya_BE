package com.nudge.wooriya.adapter.out.persistence.Repo;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CafeRepository extends MongoRepository<Cafe, String> {
    Optional<Cafe> findByCafeId(String cafeId);

    List<Cafe> findByCafeAtmospheresContaining(List<CafeAtmosphere> cafeAtmospheres);

    Page<Cafe> findByCollaborationPolicyContains(Set<CollaborationPolicy> policies, Pageable pageable);

}
