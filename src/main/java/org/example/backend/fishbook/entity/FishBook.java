package org.example.backend.fishbook.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}
