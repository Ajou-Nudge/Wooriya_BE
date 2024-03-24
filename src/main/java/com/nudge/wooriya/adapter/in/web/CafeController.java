package com.nudge.wooriya.adapter.in.web;

import com.nudge.wooriya.application.port.in.Cafe.dto.CafeDetailsDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafePreviewDto;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeUpdateDto;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.application.port.in.Cafe.CafeUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="User", description = "카페 정보 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
public class CafeController {
    private final CafeUsecase cafeService;
    @Operation(summary = "카페 상세 페이지", description = "[Token X] 카페 상세정보 가져오기")
    @GetMapping("/detail/{id}")
    public ResponseEntity<CafeDetailsDto> cafeDetail(@PathVariable String id)throws Exception {
        CafeDetailsDto cafeDetailsDto = cafeService.getCafeDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(cafeDetailsDto);
    }

    @Operation(summary = "카페 미리보기", description = "[Token X] 카페 미리보기 가져오기")
    @GetMapping("/previews")
    public ResponseEntity<List<CafePreviewDto>> getVerifiedCafePreviews() {
        List<CafePreviewDto> cafePreviews = cafeService.getVerifiedCafePreviews();
        return ResponseEntity.ok(cafePreviews);
    }
//
//    @Operation(summary = "카페 프로필", description = "[Token X] 카페 프로필 가져오기")
//    @GetMapping("/profile/{email}")
//    public ResponseEntity<CafeProfileDto> cafeProfile(@PathVariable String email) throws Exception {
//        CafeProfileDto cafeProfileDto = cafeService.getCafeProfile(email);
//        return ResponseEntity.status(HttpStatus.OK).body(cafeProfileDto);
//    }

    @Operation(summary = "카페 정보 추가", description = "[Token X] 새로운 카페 정보를 추가합니다.")
    @PostMapping("/admin")
    public ResponseEntity<Cafe> addCafe(@RequestBody Cafe cafe) {
        Cafe savedCafe = cafeService.saveCafe(cafe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCafe);
    }

    @Operation(summary = "모든 카페 정보 조회", description = "[Token X] 등록된 모든 카페의 정보를 조회합니다.")
    @GetMapping("/admin")
    public ResponseEntity<List<Cafe>> getAllCafes() {
        List<Cafe> cafes = cafeService.getAllCafes();
        return ResponseEntity.ok(cafes);
    }
    @Operation(summary = "카페 정보 업데이트", description = "[Token X] 기존 카페 정보를 업데이트합니다.")
    @PostMapping("/admin/{id}")
    public ResponseEntity<Cafe> updateCafe(@PathVariable String id, @RequestBody Cafe cafeDetails) {
        Cafe updatedCafe = cafeService.updateCafe(id, cafeDetails);
        return ResponseEntity.ok(updatedCafe);
    }

    @Operation(summary = "카페 정보 삭제", description = "[Token X] 카페 정보를 삭제합니다.")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteCafe(@PathVariable String id) {
        cafeService.deleteCafe(id);
        return ResponseEntity.ok("Cafe deleted successfully");
    }
    @Operation(summary = "카페 디테일 정보 추가", description = "[Token X] 카페 디테일 정보를 추가함")
    @PostMapping("/admin/Detail/{id}")
    public ResponseEntity<CafeUpdateDto> updateAdditionalCafeData(@PathVariable String id, @RequestBody CafeUpdateDto cafeUpdateDetail) throws Exception{
        CafeUpdateDto cafeUpdateDto = cafeService.updateAdditionalCafeData(id, cafeUpdateDetail);
        return ResponseEntity.status(HttpStatus.OK).body(cafeUpdateDto);
    }

    @Operation(summary = "승인이 안된 카페 정보 출력", description = "[Token X] 승인이 안된 카페정보 출력")
    @GetMapping("/admin/NotVerify")
    public ResponseEntity<List<Cafe>> getNotVerifiedCafe() throws Exception {
        List<Cafe> cafes = cafeService.getNotVerifiedCafe();
        return ResponseEntity.status(HttpStatus.OK).body(cafes);
    }


}
