package com.nudge.wooriya.adapter.in.web.cafe;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Cafe;
import com.nudge.wooriya.application.port.in.Cafe.CafeUsecase;
import com.nudge.wooriya.application.port.in.Cafe.dto.CafeUpdateDto;
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
@RequestMapping("/cafe/admin")
public class AdminController {
    private final CafeUsecase cafeUsecase;
    @Operation(summary = "카페 정보 추가", description = "[Token X] 새로운 카페 정보를 추가합니다.")
    @PostMapping("/create")
    public ResponseEntity<Cafe> saveCafe(@RequestBody Cafe cafe) {
        Cafe savedCafe = cafeUsecase.saveCafe(cafe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCafe);
    }

    @Operation(summary = "모든 카페 정보 조회", description = "[Token X] 등록된 모든 카페의 정보를 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity<List<Cafe>> getAllCafes() {
        List<Cafe> cafes = cafeUsecase.getAllCafes();
        return ResponseEntity.ok(cafes);
    }
    @Operation(summary = "카페 정보 업데이트", description = "[Token X] 기존 카페 정보를 업데이트합니다.")
    @PostMapping("/update/{id}")
    public ResponseEntity<Cafe> updateCafe(@PathVariable String id, @RequestBody Cafe cafeDetails) {
        Cafe updatedCafe = cafeUsecase.updateCafe(id, cafeDetails);
        return ResponseEntity.ok(updatedCafe);
    }

    @Operation(summary = "카페 정보 삭제", description = "[Token X] 카페 정보를 삭제합니다.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCafe(@PathVariable String id) {
        cafeUsecase.deleteCafe(id);
        return ResponseEntity.ok("Cafe deleted successfully");
    }

    @Operation(summary = "카페 디테일 정보 추가", description = "[Token X] 카페 디테일 정보를 추가함")
    @PostMapping("/detail/{id}")
    public ResponseEntity<CafeUpdateDto> updateAdditionalCafeData(@PathVariable String id, @RequestBody CafeUpdateDto cafeUpdateDetail) throws Exception{
        CafeUpdateDto cafeUpdateDto = cafeUsecase.updateAdditionalCafeData(id, cafeUpdateDetail);
        return ResponseEntity.status(HttpStatus.OK).body(cafeUpdateDto);
    }

    //    @Operation(summary = "승인이 안된 카페 정보 출력", description = "[Token X] 승인이 안된 카페정보 출력")
//    @GetMapping("/admin/NotVerify")
//    public ResponseEntity<List<Cafe>> getNotVerifiedCafe() throws Exception {
//        List<Cafe> cafes = cafeService.getNotVerifiedCafe();
//        return ResponseEntity.status(HttpStatus.OK).body(cafes);
//    }
}
