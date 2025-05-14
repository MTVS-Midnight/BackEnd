package org.example.backend.fishbook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class FishStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private boolean caught;

    @Setter
    private LocalDateTime caughtAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fish_book_id")
    private FishBook fishBook;

    public FishStatus(FishBook fishBook) {
        this.fishBook = fishBook;
        this.caught = false;
    }
}