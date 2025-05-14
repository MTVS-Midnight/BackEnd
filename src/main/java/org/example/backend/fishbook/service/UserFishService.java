package org.example.backend.fishbook.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.dto.FishBookDetailResponse;
import org.example.backend.fishbook.dto.FishBookSummaryResponse;
import org.example.backend.fishbook.entity.FishBook;
import org.example.backend.fishbook.entity.UserFish;
import org.example.backend.fishbook.repository.FishBookRepository;
import org.example.backend.fishbook.repository.UserFishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserFishService {
    private final UserFishRepository userFishRepository;
    private final FishBookRepository fishBookRepository;

    // 물고기 도감 전체 조회
    @Transactional(readOnly = true)
    public List<FishBookSummaryResponse> getUserFishBook(long userId) {
        List<FishBook> allFish = fishBookRepository.findAll();
        Set<Long> caughtIds = userFishRepository.findCaughtFishIdsByUser(userId);

        return allFish.stream().map(fish ->
                new FishBookSummaryResponse(
                        fish.getId(),
                        caughtIds.contains(fish.getId()) ? fish.getName() : null,
                        caughtIds.contains(fish.getId())
                )).toList();
    }

    //물고기 도감 상세 조회
    @Transactional(readOnly = true)
    public FishBookDetailResponse getFishDetails(Long userId, Long fishId) {
        // UserFish 조회
        UserFish userFish = userFishRepository.findByUserIdAndFishBookId(userId, fishId)
                .orElseThrow(() -> new RuntimeException("해당 물고기를 찾을 수 없습니다."));

        // 해금 여부 확인
        if (!userFish.isCaught()) {
            throw new RuntimeException("해당 물고기는 아직 해금되지 않았습니다");
        }

        // 물고기 상세 정보 반환
        FishBook fish = userFish.getFishBook();
        return new FishBookDetailResponse(
                fish.getId(),
                fish.getName(),
                fish.getHabitat(),
                fish.getDescription(),
                fish.getImageUrl()
        );
    }

}
