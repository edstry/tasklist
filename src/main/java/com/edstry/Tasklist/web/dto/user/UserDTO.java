package com.edstry.Tasklist.web.dto.user;

import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "User DTO")
public class UserDTO {

    @Schema(description = "User id", example = "1")
    @NotNull(message = "Id should not be null", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "User name", example = "John Doe")
    @NotNull(message = "Id should not be null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length should be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @Schema(description = "User email", example = "johndoe@gmail.com")
    @NotNull(message = "Username should not be null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length should be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @Schema(description = "User crypted password", example = "$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password should not be null", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(name = "User password confirmation", example = "$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation should not be null", groups = {OnCreate.class})
    private String passwordConfirmation;
}
