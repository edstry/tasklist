package com.edstry.Tasklist.web.dto.user;

import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {

    @NotNull(message = "Id should not be null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Id should not be null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length should be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @NotNull(message = "Username should not be null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length should be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password should not be null", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation should not be null", groups = {OnCreate.class})
    private String passwordConfirmation;
}
