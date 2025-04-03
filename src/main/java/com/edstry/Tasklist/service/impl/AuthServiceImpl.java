package com.edstry.Tasklist.service.impl;

import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.service.AuthService;
import com.edstry.Tasklist.service.UserService;
import com.edstry.Tasklist.web.dto.auth.JwtRequest;
import com.edstry.Tasklist.web.dto.auth.JwtResponse;
import com.edstry.Tasklist.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        // После вызова (.authenticate) перенаправляем Spring на JwtUserDetailService
        // метод loadUserByUsername(), проверит username, затем password
        // с помощью passwordEncoder, если всё хорошо, то пойдёт дальше
        // если нет то вернёт исключение.
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword())
        );
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(
                user.getId(),
                user.getUsername(),
                user.getRoles()
        ));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(
                user.getId(),
                user.getUsername()
        ));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
