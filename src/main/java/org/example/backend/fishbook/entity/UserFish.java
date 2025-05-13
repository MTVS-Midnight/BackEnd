package org.example.backend.fishbook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.backend.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Getter
public class UserFish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean caught;

    private LocalDateTime caughtAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fish_book_id")
    private FishBook fishBook;
}
