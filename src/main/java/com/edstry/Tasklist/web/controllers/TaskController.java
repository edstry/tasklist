package com.edstry.Tasklist.web.controllers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.service.TaskService;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.edstry.Tasklist.web.mappers.TaskMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping()
    public TaskDTO update(@Validated(OnUpdate.class) @RequestBody TaskDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        System.out.println(task);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }
}
