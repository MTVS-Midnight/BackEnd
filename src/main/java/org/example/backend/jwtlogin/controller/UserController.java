package org.example.backend.jwtlogin.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.jwtlogin.dto.ProfileResponse;
import org.example.backend.jwtlogin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }
}
