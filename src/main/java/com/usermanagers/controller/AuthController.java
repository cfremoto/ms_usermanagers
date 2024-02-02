package com.usermanagers.controller;

import com.usermanagers.dto.LoginRequestDto;
import com.usermanagers.dto.UserResponseDto;
import com.usermanagers.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/authenticate", produces = "application/json")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authService.login(request));
    }
}
