package org.example.backend.fishbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FishBookResponse {
    private long id;
    private String name;
    private boolean caught;
}
