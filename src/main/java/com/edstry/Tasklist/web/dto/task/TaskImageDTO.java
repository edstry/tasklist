package com.edstry.Tasklist.web.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TaskImageDTO {

    @NotNull(message = "Image should not be null.")
    private MultipartFile file;
}
