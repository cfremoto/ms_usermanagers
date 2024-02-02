package com.usermanagers.service;

import com.usermanagers.dto.LoginRequestDto;
import com.usermanagers.dto.UserResponseDto;
import com.usermanagers.exception.ForbiddenException;
import com.usermanagers.exception.ResourceNotFoundException;
import com.usermanagers.jwt.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JwtService JwtService;

    @Autowired
    UserService userService;

    public UserResponseDto login(LoginRequestDto request) {
        log.info("Authenticating user with email: {}", request.getEmail());

        UserResponseDto userDetails = userService.getUserByEmail(request.getEmail());

        if (userDetails == null) {
            throw new ResourceNotFoundException("User not found with email: " + request.getEmail());
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (org.springframework.security.core.AuthenticationException ex) {
            throw new ForbiddenException("Authentication failed: Incorrect credentials");
        }

        String token = JwtService.generateToken(userDetails);
        return UserResponseDto.builder().token(token).build();
    }
}
