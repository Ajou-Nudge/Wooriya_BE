package com.nudge.wooriya.application.port.in.Cafe;

import com.nudge.wooriya.application.port.in.Cafe.dto.CafeDetailsDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafePreviewDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeUpdateDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CafeSpot;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CafeUsecase {
    // =================================================
    // ==================CafeController=================
    // =================================================
    CafeDetailsDto getCafeDetails(String cafeId) throws Exception;
    List<CafePreviewDto> getCafePreviews();
    List<CafePreviewDto> getCafesByDynamicFilters(Set<CollaborationPolicy> policies, CafeSpot cafeSpot, Double size, Integer yearsInBusiness, Set<CafeAtmosphere> cafeAtmosphere, Pageable pageable);

    // =================================================
    // ==================AdminController=================
    // =================================================
    CafeUpdateDto updateAdditionalCafeData(String cafeId, CafeUpdateDto cafeUpdateDto) throws Exception;
    Cafe saveCafe(Cafe cafe);
    Cafe getCafeById(String cafeId);
    List<Cafe> getAllCafes();
    Cafe updateCafe(String cafeId, Cafe cafe);
    void deleteCafe(String cafeId);

    Page<Cafe> getCafesByCollaborationPolicy(Set<CollaborationPolicy> policies, int page, int size);
//    List<Cafe> getNotVerifiedCafe() throws Exception;
//    CafeProfileDto getCafeProfile(String email) throws Exception;
}
