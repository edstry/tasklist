package com.edstry.Tasklist.web.controllers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.service.TaskService;
import com.edstry.Tasklist.service.UserService;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import com.edstry.Tasklist.web.dto.user.UserDTO;
import com.edstry.Tasklist.web.dto.validation.OnCreate;
import com.edstry.Tasklist.web.dto.validation.OnUpdate;
import com.edstry.Tasklist.web.mappers.TaskMapper;
import com.edstry.Tasklist.web.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;


    @PutMapping()
    public UserDTO update(@Validated(OnUpdate.class) @RequestBody UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{userId}/tasks")
    public TaskDTO create(@PathVariable Long userId, @Validated(OnCreate.class) @RequestBody TaskDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task, userId);
        return taskMapper.toDto(createdTask);
    }

}
