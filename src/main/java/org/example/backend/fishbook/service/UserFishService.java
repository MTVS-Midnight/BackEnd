package org.example.backend.fishbook.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.dto.FishBookResponse;
import org.example.backend.fishbook.entity.FishBook;
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

    @Transactional(readOnly = true)
    public List<FishBookResponse> getUserFishBook(long userId) {
        List<FishBook> allFish = fishBookRepository.findAll();
        Set<Long> caughtIds = userFishRepository.findCaughtFishIdsByUser(userId);

        return allFish.stream().map(fish ->
                new FishBookResponse(
                        fish.getId(),
                        caughtIds.contains(fish.getId()) ? fish.getName() : null,
                        caughtIds.contains(fish.getId())
                )).toList();
    }
}
