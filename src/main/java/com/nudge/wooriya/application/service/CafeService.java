package com.nudge.wooriya.application.service;

import com.nudge.wooriya.adapter.out.persistence.ViewCountService;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeDetailsDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafePreviewDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeUpdateDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.adapter.out.persistence.Repo.CafeRepository;
import com.nudge.wooriya.application.port.in.Cafe.CafeUsecase;
import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CafeSpot;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CafeService implements CafeUsecase {

    private final CafeRepository cafeRepository;
    private final ViewCountService viewCountService;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CafeService(CafeRepository cafeRepository, ViewCountService viewCountService, MongoTemplate mongoTemplate) {
        this.cafeRepository = cafeRepository;
        this.viewCountService = viewCountService;
        this.mongoTemplate = mongoTemplate;
    }
    // =================================================
    // ==================CafeController=================
    // =================================================
    @Override
    public List<CafePreviewDto> getCafePreviews(){
        List<Cafe> CafePreviews = cafeRepository.findAll().stream()
                .collect(Collectors.toList());

        return CafePreviews.stream().map(this::convertToPreviewDto).collect(Collectors.toList());
    }


    @Override
    public CafeDetailsDto getCafeDetails(String cafeId) throws Exception {
        Optional<Cafe> cafeOptional = cafeRepository.findByCafeId(cafeId);
        if (cafeOptional.isPresent()) {
            Cafe cafe = cafeOptional.get();
            CafeDetailsDto cafeDetailsDto = new CafeDetailsDto();
            cafeDetailsDto.setCafeId(cafe.getCafeId());
            cafeDetailsDto.setSize(cafe.getSize());
            cafeDetailsDto.setCafeOneLineIntroduction(cafe.getCafeOneLineIntroduction());
            cafeDetailsDto.setIntroduction(cafe.getIntroduction());
            cafeDetailsDto.setPurposeAnswer(cafe.getPurposeAnswer());
            cafeDetailsDto.setCollaborationPolicyAnswer(cafe.getCollaborationPolicyAnswer());
            cafeDetailsDto.setCafeAtmospheresAnswer(cafe.getCafeAtmospheresAnswer());
            cafeDetailsDto.setHopePartnerAnswer(cafe.getHopePartnerAnswer());
            cafeDetailsDto.setCafeAtmospheres(cafe.getCafeAtmospheres());
            cafeDetailsDto.setAddress(cafe.getAddress());
            cafeDetailsDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
            cafeDetailsDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
            cafeDetailsDto.setYearsInBusiness(cafe.getYearsInBusiness());
            cafeDetailsDto.setPhotos(cafe.getPhotos());
            cafeDetailsDto.setContactHours(cafe.getContactHours());
            cafeDetailsDto.setHolidays(cafe.getHolidays());
            cafeDetailsDto.setCafeName(cafe.getCafeName());
            cafeDetailsDto.setProfilePhoto(cafe.getProfilePhoto());
            cafeDetailsDto.setEmail(cafe.getEmail());

            // 카페 분위기와 관련된 다른 카페들의 프리뷰 가져오기
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
    public List<CafePreviewDto> getCafesByDynamicFilters(Set<CollaborationPolicy> policies, CafeSpot cafeSpot, Double size, Integer yearsInBusiness, Set<CafeAtmosphere> cafeAtmosphere, Pageable pageable) {
        Query query = new Query().with(pageable);
        if(cafeAtmosphere != null){
            query.addCriteria(Criteria.where("cafeAtmospheres").in(cafeAtmosphere));
        }
        if (policies != null && !policies.isEmpty()) {
            query.addCriteria(Criteria.where("collaborationPolicy").in(policies));
        }
        if (cafeSpot != null) {
            query.addCriteria(Criteria.where("cafeSpot").is(cafeSpot));
        }
        if (size != null) {
            query.addCriteria(Criteria.where("size").is(size));
        }
        if (yearsInBusiness != null && yearsInBusiness > 0) {
            query.addCriteria(Criteria.where("yearsInBusiness").gte(yearsInBusiness));
        }


        List<Cafe> cafes = mongoTemplate.find(query, Cafe.class);
        return cafes.stream()
                .map(this::convertToPreviewDto)
                .collect(Collectors.toList());
    }

    // =================================================
    // ==================AdminController=================
    // =================================================
    @Override
    public Cafe saveCafe(Cafe cafe) {
        if (cafe.getCafeId() != null && cafeRepository.findByCafeId(cafe.getCafeId()).isPresent()) {
            throw new RuntimeException("Cafe with id " + cafe.getCafeId() + " already exists.");
        }

        return cafeRepository.save(cafe);
    }

    @Override
    public Cafe getCafeById(String cafeId) {
        Cafe cafe = cafeRepository.findByCafeId(cafeId)
                .orElseThrow(() -> new RuntimeException("Cafe not found with id: " + cafeId));

        viewCountService.incrementViewCount(cafeId);
        return cafe;
    }
    @Override
    public Page<Cafe> getCafesByCollaborationPolicy(Set<CollaborationPolicy> policies, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cafeRepository.findByCollaborationPolicyContains(policies, pageable);
    }

    @Override
    public List<Cafe> getAllCafes() {
        return cafeRepository.findAll();
    }

    @Override
    public Cafe updateCafe(String cafeId, Cafe cafe) {
        Optional<Cafe> existingCafeOptional = cafeRepository.findByCafeId(cafeId);

        if (existingCafeOptional.isPresent()) {
            Cafe existingCafe = existingCafeOptional.get();

            existingCafe.setSize(cafe.getSize());
            existingCafe.setCafeOneLineIntroduction(cafe.getCafeOneLineIntroduction());
            existingCafe.setIntroduction(cafe.getIntroduction());
            existingCafe.setPurposeAnswer(cafe.getPurposeAnswer());
            existingCafe.setCollaborationPolicyAnswer(cafe.getCollaborationPolicyAnswer());
            existingCafe.setCafeAtmospheresAnswer(cafe.getCafeAtmospheresAnswer());
            existingCafe.setHopePartnerAnswer(cafe.getHopePartnerAnswer());
            existingCafe.setCafeAtmospheres(cafe.getCafeAtmospheres());
            existingCafe.setAddress(cafe.getAddress());
            existingCafe.setMaximumOccupancy(cafe.getMaximumOccupancy());
            existingCafe.setYearsInBusiness(cafe.getYearsInBusiness());
            existingCafe.setCollaborationPolicy(cafe.getCollaborationPolicy());
            existingCafe.setPhotos(cafe.getPhotos());
            existingCafe.setContactHours(cafe.getContactHours());
            existingCafe.setHolidays(cafe.getHolidays());
            existingCafe.setCafeName(cafe.getCafeName());
            existingCafe.setProfilePhoto(cafe.getProfilePhoto());

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
    public CafeUpdateDto updateAdditionalCafeData(String cafeId, CafeUpdateDto cafeUpdateDto) throws Exception {
        Optional<Cafe> cafeOptional = cafeRepository.findByCafeId(cafeId);
        if (cafeOptional.isPresent()) {
            Cafe cafe = cafeOptional.get();

            cafe.setSize(cafeUpdateDto.getSize());
            cafe.setCafeOneLineIntroduction(cafeUpdateDto.getCafeOneLineIntroduction());
            cafe.setIntroduction(cafeUpdateDto.getIntroduction());
            cafe.setPurposeAnswer(cafeUpdateDto.getPurposeAnswer());
            cafe.setCollaborationPolicyAnswer(cafeUpdateDto.getCollaborationPolicyAnswer());
            cafe.setCafeAtmospheresAnswer(cafeUpdateDto.getCafeAtmospheresAnswer());
            cafe.setHopePartnerAnswer(cafeUpdateDto.getHopePartnerAnswer());
            cafe.setCafeAtmospheres(cafeUpdateDto.getCafeAtmospheres());
            cafe.setMaximumOccupancy(cafeUpdateDto.getMaximumOccupancy());
            cafe.setCollaborationPolicy(cafeUpdateDto.getCollaborationPolicy());
            cafe.setPhotos(cafeUpdateDto.getPhotos());
            cafe.setContactHours(cafeUpdateDto.getContactHours());
            cafe.setHolidays(cafeUpdateDto.getHolidays());
            cafe.setProfilePhoto(cafeUpdateDto.getProfilePhoto());

            Cafe updatedCafe = cafeRepository.save(cafe);
            return convertToCafeUpdateDto(updatedCafe);
        } else {
            throw new Exception("Cafe not found with id: " + cafeId);
        }
    }

    private CafeUpdateDto convertToCafeUpdateDto(Cafe cafe) {
        CafeUpdateDto cafeUpdateDto = new CafeUpdateDto();
        cafeUpdateDto.setSize(cafe.getSize());
        cafeUpdateDto.setCafeOneLineIntroduction(cafe.getCafeOneLineIntroduction());
        cafeUpdateDto.setIntroduction(cafe.getIntroduction());
        cafeUpdateDto.setPurposeAnswer(cafe.getPurposeAnswer());
        cafeUpdateDto.setCollaborationPolicyAnswer(cafe.getCollaborationPolicyAnswer());
        cafeUpdateDto.setCafeAtmospheresAnswer(cafe.getCafeAtmospheresAnswer());
        cafeUpdateDto.setHopePartnerAnswer(cafe.getHopePartnerAnswer());
        cafeUpdateDto.setCafeAtmospheres(cafe.getCafeAtmospheres());
        cafeUpdateDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
        cafeUpdateDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
        cafeUpdateDto.setPhotos(cafe.getPhotos());
        cafeUpdateDto.setContactHours(cafe.getContactHours());
        cafeUpdateDto.setHolidays(cafe.getHolidays());
        cafeUpdateDto.setProfilePhoto(cafe.getProfilePhoto());
        return cafeUpdateDto;
    }

//    @Override
//    public List<Cafe> getNotVerifiedCafe() throws Exception{
//        List<Cafe> notVerifiedCafes = cafeRepository.findByIsVerifiedFalse();
//        if (notVerifiedCafes.isEmpty()) {
//            throw new Exception("No not verified cafes found");
//        }
//        return notVerifiedCafes;
//    }

    private CafePreviewDto convertToPreviewDto(Cafe cafe) {
        CafePreviewDto previewDto = new CafePreviewDto();
        previewDto.setCafeId(cafe.getCafeId());
        previewDto.setCafeName(cafe.getCafeName());
        previewDto.setAddress(cafe.getAddress());
        previewDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
        previewDto.setYearsInBusiness(cafe.getYearsInBusiness());
        previewDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
        previewDto.setCafeAtmospheres(new HashSet<>(cafe.getCafeAtmospheres()));
        if (!cafe.getPhotos().isEmpty()) {
            previewDto.setMainPhoto(cafe.getPhotos().get(0)); // 주의: 사진 리스트가 비어있지 않은지 확인
        }
        return previewDto;
    }

//    @Override
//    public CafeProfileDto getCafeProfile(String email) throws Exception {
//        // cafeId를 이용하여 카페 정보 조회
//        Optional<Cafe> cafeOptional = cafeRepository.findByCafeId(email);
//
//        if (cafeOptional.isPresent()) {
//            Cafe cafe = cafeOptional.get();
//            CafeProfileDto cafeProfileDto = new CafeProfileDto();
//            cafeProfileDto.setTradeName(cafe.getTradeName());
//            cafeProfileDto.setCafeName(cafe.getCafeName());
//            cafeProfileDto.setAddress(cafe.getAddress());
//            cafeProfileDto.setMaximumOccupancy(cafe.getMaximumOccupancy());
//            cafeProfileDto.setCollaborationPolicy(cafe.getCollaborationPolicy());
//            cafeProfileDto.setIntroduction(cafe.getIntroduction());
//            cafeProfileDto.setProfilePhoto(cafe.getProfilePhoto());
//            cafeProfileDto.setRelatedLinks(cafe.getRelatedLinks());
//            cafeProfileDto.setContactHours(cafe.getContactHours());
//            cafeProfileDto.setHolidays(cafe.getHolidays());
//
//            return cafeProfileDto;
//        } else {
//            throw new Exception("not Found " + email);
//        }
//    }

}
