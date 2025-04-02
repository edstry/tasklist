package com.edstry.Tasklist.service;

import com.edstry.Tasklist.web.dto.auth.JwtRequest;
import com.edstry.Tasklist.web.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);
}
