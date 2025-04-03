package com.edstry.Tasklist.web.controllers;

import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.service.AuthService;
import com.edstry.Tasklist.service.UserService;
import com.edstry.Tasklist.web.dto.auth.JwtRequest;
import com.edstry.Tasklist.web.dto.auth.JwtResponse;
import com.edstry.Tasklist.web.dto.token.RefreshTokenDto;
import com.edstry.Tasklist.web.dto.user.UserDTO;
import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.edstry.Tasklist.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@Validated(OnCreate.class) @RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        System.out.println(user);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody @Validated(OnUpdate.class) RefreshTokenDto refreshToken) {
        return authService.refresh(refreshToken.getRefreshToken());
    }
}
