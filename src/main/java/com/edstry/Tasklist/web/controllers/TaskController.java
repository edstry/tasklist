package com.edstry.Tasklist.web.controllers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.service.TaskService;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.edstry.Tasklist.web.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    @PreAuthorize("canAccessTask(#id)") // Второй способ security expression(сложный)
    public TaskDTO getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
    @PreAuthorize("canAccessTask(#id)") // Второй способ security expression(сложный)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping()
    @Operation(summary = "Update task")
    @PreAuthorize("canAccessTask(#taskDto.id)") // Второй способ security expression(сложный)
    public TaskDTO update(@Validated(OnUpdate.class) @RequestBody TaskDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        System.out.println(task);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }
}
