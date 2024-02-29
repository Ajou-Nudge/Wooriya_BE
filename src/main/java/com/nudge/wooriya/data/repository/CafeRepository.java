package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Cafe;
import com.nudge.wooriya.enums.CafeAtmosphere;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends MongoRepository<Cafe, String> {
    Optional<Cafe> findByCafeId(String cafeId);

    Optional<Cafe> findByCafeAtmospheresContaining(List<CafeAtmosphere> cafeAtmospheres);

    List<Cafe> findByIsVerifiedFalse(); // 추가된 메서드
}
