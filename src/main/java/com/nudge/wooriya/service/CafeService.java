package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.cafe.CafeDetailsDto;
import com.nudge.wooriya.data.dto.cafe.CafePreviewDto;
import com.nudge.wooriya.data.dto.cafe.CafeProfileDto;
import com.nudge.wooriya.data.dto.cafe.CafeUpdateDto;
import com.nudge.wooriya.data.entity.Cafe;

import java.util.List;

public interface CafeService {
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
