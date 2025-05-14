package org.example.backend.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.user.repository.UserRepository;
import org.example.backend.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtAuthenticationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if (token != null && JwtTokenProvider.validateToken(token)) {
            String username = JwtTokenProvider.getUsername(token);
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                AuthDetails authDetails = new AuthDetails(user);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        authDetails,
                        null,
                        authDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer ") ? header.substring(7) : null);
    }
}
