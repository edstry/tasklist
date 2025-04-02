package com.edstry.Tasklist.web.dto.task;

import com.edstry.Tasklist.domain.task.Status;
import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    @NotNull(message = "Id should not be null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title should not be null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Title length should be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String title;

    @Length(max = 255, message = "Description length should be smaller then 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String description;

    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
