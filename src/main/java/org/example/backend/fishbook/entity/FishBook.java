package org.example.backend.fishbook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FishBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String habitat;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    public FishBook(String name, String habitat, String description, String imageUrl) {
        this.name = name;
        this.habitat = habitat;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
