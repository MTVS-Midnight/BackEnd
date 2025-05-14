package org.example.backend.fishbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FishBookSummaryResponse {
    private long id;
    private String name;
    private boolean caught;
}
