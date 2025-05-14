package org.example.backend.fishbook.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.dto.FishBookDetailResponse;
import org.example.backend.fishbook.dto.FishBookSummaryResponse;
import org.example.backend.fishbook.entity.FishBook;
import org.example.backend.fishbook.entity.FishStatus;
import org.example.backend.fishbook.repository.FishBookRepository;
import org.example.backend.fishbook.repository.FishStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FishBookService {
    private final FishBookRepository fishBookRepository;
    private final FishStatusRepository fishStatusRepository;

    // 물고기 도감 전체 조회
    @Transactional(readOnly = true)
    public List<FishBookSummaryResponse> getFishBook() {
        List<FishBook> allFish = fishBookRepository.findAll();
        Set<Long> caughtIds = fishStatusRepository.findCaughtFishIds();

        return allFish.stream().map(fish ->
                new FishBookSummaryResponse(
                        fish.getId(),
                        caughtIds.contains(fish.getId()) ? fish.getName() : null,
                        caughtIds.contains(fish.getId())
                )).toList();
    }

    // 물고기 도감 상세 조회
    @Transactional(readOnly = true)
    public FishBookDetailResponse getFishDetails(Long fishId) {
        FishStatus fishStatus = fishStatusRepository.findByFishBookId(fishId)
                .orElseThrow(() -> new RuntimeException("해당 물고기를 찾을 수 없습니다."));

        if (!fishStatus.isCaught()) {
            throw new RuntimeException("해당 물고기는 아직 해금되지 않았습니다");
        }

        FishBook fish = fishStatus.getFishBook();
        return new FishBookDetailResponse(
                fish.getId(),
                fish.getName(),
                fish.getHabitat(),
                fish.getDescription(),
                fish.getImageUrl()
        );
    }

    // 임시 테스트용 물고기 도감 해금
    @Transactional
    public void unlockFish(Long fishId) {
        FishStatus fishStatus = fishStatusRepository.findByFishBookId(fishId)
                .orElseThrow(() -> new RuntimeException("물고기를 찾을 수 없습니다."));

        if (fishStatus.isCaught()) {
            throw new RuntimeException("이미 해금된 물고기입니다.");
        }

        fishStatus.setCaught(true);
        fishStatus.setCaughtAt(LocalDateTime.now());
        fishStatusRepository.save(fishStatus);
    }
}