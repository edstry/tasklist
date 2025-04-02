package com.edstry.Tasklist.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

    @NotNull(message = "Username should not be null")
    private String username;

    @NotNull(message = "Password should not be null")
    private String password;
}
