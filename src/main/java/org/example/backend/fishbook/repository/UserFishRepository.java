package org.example.backend.fishbook.repository;

import org.example.backend.fishbook.entity.UserFish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserFishRepository extends JpaRepository<UserFish, Long> {
    @Query("SELECT uf.fishBook.id FROM UserFish uf WHERE uf.user.id = :userId AND uf.caught = true")
    Set<Long> findCaughtFishIdsByUser(@Param("userId") long userId);
    //findByUserIdAndFishBookId: 특정 사용자의 특정 물고기(UserFish)를 조회해 caught 상태 확인.
    //상세 조회에서 caught=true 여부 확인에 사용.
    @Query("SELECT uf FROM UserFish uf WHERE uf.user.id = :userId AND uf.fishBook.id = :fishId")
    Optional<UserFish> findByUserIdAndFishBookId(@Param("userId") Long userId, @Param("fishId") Long fishId);
}

