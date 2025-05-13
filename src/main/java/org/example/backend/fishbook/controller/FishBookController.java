package org.example.backend.fishbook.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.dto.FishBookResponse;
import org.example.backend.fishbook.entity.FishBook;
import org.example.backend.fishbook.repository.FishBookRepository;
import org.example.backend.fishbook.repository.UserFishRepository;
import org.example.backend.fishbook.service.UserFishService;
import org.example.backend.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fishbook")
@RequiredArgsConstructor
public class FishBookController {

    private final UserFishService userFishService;
    private final FishBookRepository fishBookRepository;
    private final UserFishRepository userFishRepository;

    // 1. 물고기 도감 전체 조회
    @GetMapping
    public ResponseEntity<List<FishBookResponse>> getFishBook(@AuthenticationPrincipal User user) {
        List<FishBookResponse> fishBook = userFishService.getUserFishBook(user.getId());
        return ResponseEntity.ok(fishBook);
    }

    // 2. 물고기 도감 상세 조회
    @GetMapping("/{fishId}")
    public ResponseEntity<FishBook> getFishDetails(
            @PathVariable Long fishId,
            @AuthenticationPrincipal User user) {
        FishBook fishDetails = userFishService.getFishDetails(user.getId(), fishId);
        return ResponseEntity.ok(fishDetails);
    }

}
