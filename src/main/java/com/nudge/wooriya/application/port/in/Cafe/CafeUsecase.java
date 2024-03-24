package com.nudge.wooriya.application.port.in.Cafe;

import com.nudge.wooriya.application.port.in.Cafe.dto.CafeDetailsDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafePreviewDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeUpdateDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;

import java.util.List;

public interface CafeUsecase {
    Cafe saveCafe(Cafe cafe);
    Cafe getCafeById(String cafeId);
    List<Cafe> getAllCafes();
    Cafe updateCafe(String cafeId, Cafe cafe);
    void deleteCafe(String cafeId);
    List<CafePreviewDto> getVerifiedCafePreviews();
    CafeDetailsDto getCafeDetails(String cafeId) throws Exception;
    CafeUpdateDto updateAdditionalCafeData(String cafeId, CafeUpdateDto cafeUpdateDto) throws Exception;

    List<Cafe> getNotVerifiedCafe() throws Exception;
//    CafeProfileDto getCafeProfile(String email) throws Exception;
}
