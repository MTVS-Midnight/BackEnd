package org.example.backend.fishbook.repository;

import org.example.backend.fishbook.entity.FishStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface FishStatusRepository extends JpaRepository<FishStatus, Long> {
    @Query("SELECT fs.fishBook.id FROM FishStatus fs WHERE fs.caught = true")
    Set<Long> findCaughtFishIds();

    @Query("SELECT fs FROM FishStatus fs WHERE fs.fishBook.id = :fishId")
    Optional<FishStatus> findByFishBookId(@Param("fishId") Long fishId);
}