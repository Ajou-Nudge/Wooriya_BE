package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.cafe.CafeDetailsDto;
import com.nudge.wooriya.data.dto.cafe.CafePreviewDto;
import com.nudge.wooriya.data.dto.cafe.CafeProfileDto;
import com.nudge.wooriya.data.entity.Cafe;

import java.util.List;
import java.util.Optional;

public interface CafeService {
    Cafe saveCafe(Cafe cafe);
    Cafe getCafeById(String cafeId);
    List<Cafe> getAllCafes();
    Cafe updateCafe(String cafeId, Cafe cafe);
    void deleteCafe(String cafeId);
    List<CafePreviewDto> getVerifiedCafePreviews();
    CafeDetailsDto getCafeDetails(String cafeId) throws Exception;
    CafeProfileDto getCafeProfile(String cafeId) throws Exception;
}
