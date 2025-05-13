package org.example.backend.fishbook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class UserFish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private boolean caught;

    @Setter
    private LocalDateTime caughtAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fish_book_id")
    private FishBook fishBook;

    public UserFish(User user, FishBook fishBook) {
        this.user = user;
        this.fishBook = fishBook;
    }
}
