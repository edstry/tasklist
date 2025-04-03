package com.edstry.Tasklist.web.dto.token;

import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenDto {

    @NotNull(message = "Token should not be null", groups = {OnUpdate.class, OnCreate.class})
    String refreshToken;
}
