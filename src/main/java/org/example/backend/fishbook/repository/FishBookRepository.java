package org.example.backend.fishbook.repository;

import org.example.backend.fishbook.entity.FishBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishBookRepository extends JpaRepository<FishBook, Long> {
}
