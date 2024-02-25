package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.data.dto.cafe.CafeDetailsDto;
import com.nudge.wooriya.data.dto.cafe.CafePreviewDto;
import com.nudge.wooriya.data.dto.cafe.CafeProfileDto;
import com.nudge.wooriya.data.entity.Cafe;
import com.nudge.wooriya.data.repository.CafeRepository;
import com.nudge.wooriya.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;

    @Autowired
    public CafeServiceImpl(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    public Cafe saveCafe(Cafe cafe) {
        if (cafe.getCafeId() != null && cafeRepository.findByCafeId(cafe.getCafeId()).isPresent()) {
            throw new RuntimeException("Cafe with id " + cafe.getCafeId() + " already exists.");
        }

        if (cafe.getCafeId() == null || cafe.getCafeId().isEmpty()) {
            cafe.setCafeId(UUID.randomUUID().toString());
        }

        return cafeRepository.save(cafe);
    }

    @Override
    public Cafe getCafeById(String cafeId) {
        return cafeRepository.findByCafeId(cafeId)
                .orElseThrow(() -> new RuntimeException("Cafe not found with id: " + cafeId));
    }

    @Override
    public List<Cafe> getAllCafes() {
        return cafeRepository.findAll();
    }

    @Override
    public Cafe updateCafe(String cafeId, Cafe cafeDetails) {
        Optional<Cafe> existingCafeOptional = cafeRepository.findByCafeId(cafeId);

        if (existingCafeOptional.isPresent()) {
            Cafe existingCafe = existingCafeOptional.get();

            existingCafe.setMainPhotos(cafeDetails.getMainPhotos());
            existingCafe.setIntroduction(cafeDetails.getIntroduction());
            existingCafe.setCafeAtmospheres(cafeDetails.getCafeAtmospheres());
            existingCafe.setAddress(cafeDetails.getAddress());
            existingCafe.setMaximumOccupancy(cafeDetails.getMaximumOccupancy());
            existingCafe.setYearsInBusiness(cafeDetails.getYearsInBusiness());
            existingCafe.setCollaborationPolicy(cafeDetails.getCollaborationPolicy());
            existingCafe.setPhotos(cafeDetails.getPhotos());
            existingCafe.setRelatedSNSLinks(cafeDetails.getRelatedSNSLinks());
            existingCafe.setContactHours(cafeDetails.getContactHours());
            existingCafe.setHolidays(cafeDetails.getHolidays());
            existingCafe.setTradeName(cafeDetails.getTradeName());
            existingCafe.setProfilePhoto(cafeDetails.getProfilePhoto());
            existingCafe.setRelatedLinks(cafeDetails.getRelatedLinks());

            return cafeRepository.save(existingCafe);
        } else {

            throw new RuntimeException("Cafe not found with id: " + cafeId);
        }
    }



    @Override
    public void deleteCafe(String cafeId) {
        Cafe cafe = cafeRepository.findByCafeId(cafeId)
                .orElseThrow(() -> new RuntimeException("Cafe not found with id " + cafeId));
        cafeRepository.delete(cafe);
    }

    @Override
    public List<CafePreviewDto> getVerifiedCafePreviews(){
        List<Cafe> verifiedCafes = cafeRepository.findAll().stream()
                .collect(Collectors.toList());

        return verifiedCafes.stream().map(this::convertToPreviewDto).collect(Collectors.toList());
    }

    private CafePreviewDto convertToPreviewDto(Cafe cafe) {
        CafePreviewDto previewDto = new CafePreviewDto();
        previewDto.setMainPhoto(cafe.getMainPhotos()); // 가정: Cafe에 mainPhotos 중 첫 번째를 사용
        previewDto.setCafeAtmospheres(cafe.getCafeAtmospheres());
        previewDto.setName(cafe.getTradeName());
        previewDto.setAddress(cafe.getAddress());
        previewDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
        previewDto.setYearsInBusiness(cafe.getYearsInBusiness());
        previewDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
        return previewDto;
    }

    @Override
    public CafeDetailsDto getCafeDetails(String cafeId) throws Exception {
        Optional<Cafe> cafeOptional = cafeRepository.findByCafeId(cafeId);
        if (cafeOptional.isPresent()) {
            Cafe cafe = cafeOptional.get();
            CafeDetailsDto cafeDetailsDto = new CafeDetailsDto();
            cafeDetailsDto.setIntroduction(cafe.getIntroduction());
            cafeDetailsDto.setCafeAtmosphere(cafe.getCafeAtmospheres());
            cafeDetailsDto.setAddress(cafe.getAddress());
            cafeDetailsDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
            cafeDetailsDto.setYearsInBusiness(cafe.getYearsInBusiness());
            cafeDetailsDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
            cafeDetailsDto.setPhotos(cafe.getPhotos());
            cafeDetailsDto.setRelatedSNSLinks(cafe.getRelatedSNSLinks());
            cafeDetailsDto.setContactHours(cafe.getContactHours());
            cafeDetailsDto.setHolidays(cafe.getHolidays());

            Set<CafePreviewDto> relatedPreviews = cafeRepository.findByCafeAtmospheresContaining(new ArrayList<>(cafe.getCafeAtmospheres()))
                    .stream()
                    .filter(otherCafe -> !otherCafe.getCafeId().equals(cafeId)) // 현재 카페를 제외
                    .limit(3) // 최대 3개
                    .map(this::convertToPreviewDto)
                    .collect(Collectors.toSet());

            cafeDetailsDto.setKeywordRelatedPreviews(relatedPreviews);
            return cafeDetailsDto;
        } else {
            throw new Exception("not Found " + cafeId);
        }
    }

    @Override
    public CafeProfileDto getCafeProfile(String cafeId) throws Exception {
        // cafeId를 이용하여 카페 정보 조회
        Optional<Cafe> cafeOptional = cafeRepository.findByCafeId(cafeId);

        if (cafeOptional.isPresent()) {
            Cafe cafe = cafeOptional.get();
            CafeProfileDto cafeProfileDto = new CafeProfileDto();
            cafeProfileDto.setTradeName(cafe.getTradeName());
            cafeProfileDto.setCafeName(cafe.getCafeName());
            cafeProfileDto.setAddress(cafe.getAddress());
            cafeProfileDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
            cafeProfileDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
            cafeProfileDto.setIntroduction(cafe.getIntroduction());
            cafeProfileDto.setProfilePhoto(cafe.getProfilePhoto());
            cafeProfileDto.setRelatedLinks(cafe.getRelatedLinks());
            cafeProfileDto.setContactHours(cafe.getContactHours());
            cafeProfileDto.setHolidays(cafe.getHolidays());

            return cafeProfileDto;
        } else {
            throw new Exception("not Found " + cafeId);
        }
    }

}
