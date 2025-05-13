package org.example.backend.user.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.user.repository.UserRepository;
import org.example.backend.user.dto.LoginRequest;
import org.example.backend.user.dto.ProfileResponse;
import org.example.backend.user.dto.SignupRequest;
import org.example.backend.user.entity.User;
import org.example.backend.user.security.JwtTokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        String encodePassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUsername(), encodePassword, request.getNickname());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        return JwtTokenProvider.createToken(user.getUsername());
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return new ProfileResponse(
                user.getUsername(),
                user.getNickname()
        );
    }
}
