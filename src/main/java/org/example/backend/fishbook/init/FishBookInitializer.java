package org.example.backend.fishbook.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.entity.FishBook;
import org.example.backend.fishbook.entity.FishStatus;
import org.example.backend.fishbook.repository.FishBookRepository;
import org.example.backend.fishbook.repository.FishStatusRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FishBookInitializer {

    private final FishBookRepository fishBookRepository;
    private final FishStatusRepository fishStatusRepository;

    @PostConstruct
    public void init() {
        if (fishBookRepository.count() > 0) return;

        // FishBook 데이터 정의 및 저장
        List<FishBook> fishBooks = List.of(
                createFish("해파리", "얕은 바다", "촉수로 상대를 마비시키는 연체동물", "jellyfish.jpg"),
                createFish("얼룩말상어", "산호초 바다", "몸에 얼룩무늬가 있어 얼룩말처럼 보이는 상어", "zebra_shark.jpg"),
                createFish("송사리", "민물", "작고 빠르며 떼를 지어 다니는 민물고기", "medaka.jpg"),
                createFish("남양쥐돔", "열대 해역", "귀여운 외형에 비해 공격적인 성격을 가진 어종", "pomacentridae.jpg"),
                createFish("귀상어", "깊은 바다", "망치 모양의 머리를 가진 특이한 상어", "hammerhead.jpg")
        );

        fishBookRepository.saveAll(fishBooks);

        // FishStatus 초기화
        List<FishStatus> fishStatuses = fishBooks.stream()
                .map(FishStatus::new)
                .toList();
        fishStatusRepository.saveAll(fishStatuses);
    }

    private FishBook createFish(String name, String habitat, String desc, String imageFileName) {
        String imageUrl = "/images/" + imageFileName;
        return new FishBook(name, habitat, desc, imageUrl);
    }
}