package com.nudge.wooriya.adapter.in.web.cafe;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeDetailsDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafePreviewDto;
import com.nudge.wooriya.application.port.in.Cafe.CafeUsecase;
import com.nudge.wooriya.common.enums.CafeAtmosphere;
import com.nudge.wooriya.common.enums.CafeSpot;
import com.nudge.wooriya.common.enums.CollaborationPolicy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.sentry.Sentry;
import java.util.List;
import java.util.Set;

@Tag(name="User", description = "카페 정보 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
public class CafeController {
    private final CafeUsecase cafeUsecase;
    @Operation(summary = "카페 상세 페이지", description = "[Token X] 카페 상세정보 가져오기")
    @GetMapping("/detail/{id}")
    public ResponseEntity<CafeDetailsDto> getCafeDetails(@PathVariable String id)throws Exception {
        CafeDetailsDto cafeDetailsDto = cafeUsecase.getCafeDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(cafeDetailsDto);
    }

    @Operation(summary = "카페 미리보기", description = "[Token X] 카페 미리보기 가져오기")
    @GetMapping("/previews")
    public ResponseEntity<List<CafePreviewDto>> getCafePreviews() {
        List<CafePreviewDto> cafePreviews = cafeUsecase.getCafePreviews();
        return ResponseEntity.ok(cafePreviews);
    }
    @Operation(summary = "카페 필터링", description = "[Token X] 카페 미리보기 가져오기")
    @GetMapping("/filter")
    public ResponseEntity<List<CafePreviewDto>> getCafesByFilters(
            @RequestParam(required = false) Set<CollaborationPolicy> policies,
            @RequestParam(required = false) CafeSpot cafeSpot,
            @RequestParam(required = false) Double placeSize,
            @RequestParam(required = false) Integer yearsInBusiness,
            @RequestParam(required = false) Set<CafeAtmosphere> cafeAtmosphere,
            @PageableDefault(size = 20, page = 0) Pageable pageable
    ) {
        List<CafePreviewDto> cafes = cafeUsecase.getCafesByDynamicFilters(policies, cafeSpot, placeSize, yearsInBusiness, cafeAtmosphere, pageable);
        return ResponseEntity.ok(cafes);
    }
    @GetMapping("/throw-exception")
    public ResponseEntity<String> throwException() {
        try {
            throw new RuntimeException("This is a test exception for monitoring purposes.");
        } catch (RuntimeException e) {
            Sentry.captureException(e);
            log.error("An exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred.");
        }
    }
//    @Operation(summary = "카페 프로필", description = "[Token X] 카페 프로필 가져오기")
//    @GetMapping("/profile/{email}")
//    public ResponseEntity<CafeProfileDto> cafeProfile(@PathVariable String email) throws Exception {
//        CafeProfileDto cafeProfileDto = cafeService.getCafeProfile(email);
//        return ResponseEntity.status(HttpStatus.OK).body(cafeProfileDto);
//    }

}
