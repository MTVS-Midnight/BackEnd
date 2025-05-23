package org.example.backend.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.user.dto.ProfileResponse;
import org.example.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }
}
