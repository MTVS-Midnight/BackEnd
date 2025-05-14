package org.example.backend.fishbook.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.dto.FishBookDetailResponse;
import org.example.backend.fishbook.dto.FishBookSummaryResponse;
import org.example.backend.fishbook.repository.FishBookRepository;
import org.example.backend.fishbook.repository.UserFishRepository;
import org.example.backend.fishbook.service.FishBookService;
import org.example.backend.fishbook.service.UserFishService;
import org.example.backend.user.entity.User;
import org.example.backend.user.security.AuthDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fishbook")
@RequiredArgsConstructor
public class FishBookController {

    private final UserFishService userFishService;
    private final FishBookRepository fishBookRepository;
    private final UserFishRepository userFishRepository;
    private final FishBookService fishBookService;
    // 1. 물고기 도감 전체 조회
//    @GetMapping
//    public ResponseEntity<List<FishBookSummaryResponse>> getFishBook(@AuthenticationPrincipal AuthDetails user) {
//        List<FishBookSummaryResponse> fishBook = userFishService.getUserFishBook(user.getUser().getId());
//        return ResponseEntity.ok(fishBook);
//    }

    // 2. 물고기 도감 상세 조회
//    @GetMapping("/{fishId}")
//    public ResponseEntity<FishBookDetailResponse> getFishDetails(
//            @PathVariable Long fishId,
//            @AuthenticationPrincipal AuthDetails user) {
//        FishBookDetailResponse fishDetails = userFishService.getFishDetails(user.getUser().getId(), fishId);
//        return ResponseEntity.ok(fishDetails);
//    }

    // 물고기 도감 전체 조회
    @GetMapping
    public ResponseEntity<List<FishBookSummaryResponse>> getFishBook() {
        List<FishBookSummaryResponse> fishBook = fishBookService.getFishBook();
        return ResponseEntity.ok(fishBook);
    }

    // 물고기 도감 상세 조회
    @GetMapping("/{fishId}")
    public ResponseEntity<FishBookDetailResponse> getFishDetails(@PathVariable Long fishId) {
        FishBookDetailResponse fishDetails = fishBookService.getFishDetails(fishId);
        return ResponseEntity.ok(fishDetails);
    }

    // 임시 테스트용 물고기 도감 해금
    @PostMapping("/unlock")
    public ResponseEntity<Map<String, String>> unlockFish(@RequestBody UnlockRequest request) {
        fishBookService.unlockFish(request.fishId());
        return ResponseEntity.ok(Map.of("message", "Fish unlocked successfully"));
    }
    // 요청 DTO
    record UnlockRequest(Long fishId) {}
}
