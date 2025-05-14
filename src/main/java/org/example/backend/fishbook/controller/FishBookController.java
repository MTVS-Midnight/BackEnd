package org.example.backend.fishbook.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.dto.FishBookDetailResponse;
import org.example.backend.fishbook.dto.FishBookSummaryResponse;
import org.example.backend.fishbook.service.FishBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fishbook")
@RequiredArgsConstructor
public class FishBookController {

    private final FishBookService fishBookService;

    // 물고기 도감 전체 조회
    @GetMapping
    public ResponseEntity<List<FishBookSummaryResponse>> getFishBook() {
        List<FishBookSummaryResponse> fishBook = fishBookService.getFishBook();
        return ResponseEntity.ok(fishBook);
    }

    // 물고기 도감 상세 조회
    @GetMapping("/{fishId}")
    public ResponseEntity<FishBookDetailResponse> getFishDetails(@PathVariable long fishId) {
        FishBookDetailResponse fishDetails = fishBookService.getFishDetails(fishId);
        return ResponseEntity.ok(fishDetails);
    }

    // 임시 테스트용 물고기 도감 해금
    @PostMapping("/{fishId}/unlock")
    public ResponseEntity<Map<String, String>> unlockFish(@PathVariable long fishId) {
        fishBookService.unlockFish(fishId);
        return ResponseEntity.ok(Map.of("message", "물고기가 해금되었습니다"));
    }
}
