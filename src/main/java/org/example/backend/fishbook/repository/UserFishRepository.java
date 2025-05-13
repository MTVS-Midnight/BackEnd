package org.example.backend.fishbook.repository;

import org.example.backend.fishbook.entity.UserFish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserFishRepository extends JpaRepository<UserFish, Long> {
    @Query("SELECT uf.fishBook.id FROM UserFish uf WHERE uf.user.id = :userId AND uf.caught = true")
    Set<Long> findCaughtFishIdsByUser(@Param("userId") long userId);
}
