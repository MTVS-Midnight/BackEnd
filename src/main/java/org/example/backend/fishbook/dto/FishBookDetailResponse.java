package org.example.backend.fishbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FishBookDetailResponse {
    private long id;
    private String name;
    private String habitat;
    private String description;
    private String imageUrl;
}
