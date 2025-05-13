package org.example.backend.fishbook.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.fishbook.repository.FishBookRepository;
import org.example.backend.fishbook.repository.UserFishRepository;
import org.example.backend.fishbook.service.UserFishService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fishbook")
@RequiredArgsConstructor
public class FishBookController {

    private final UserFishService userFishService;
    private final FishBookRepository fishBookRepository;
    private final UserFishRepository userFishRepository;


}
