package com.edstry.Tasklist.web.controllers;

import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.service.AuthService;
import com.edstry.Tasklist.service.UserService;
import com.edstry.Tasklist.web.dto.auth.JwtRequest;
import com.edstry.Tasklist.web.dto.auth.JwtResponse;
import com.edstry.Tasklist.web.dto.user.UserDTO;
import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@Validated(OnCreate.class) @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
