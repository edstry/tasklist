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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final ModelMapper modelMapper;

    @PutMapping()
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userDto.id)")
    public UserDTO update(@Validated(OnUpdate.class) @RequestBody UserDTO userDto) {
        User user = modelMapper.map(userDto, User.class);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDTO getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all tasks")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDTO> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        //modelMapper.map(tasks, new TypeToken<List<UserDTO>>(){}.getType());
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{userId}/tasks")
    @Operation(summary = "Add task to user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userId)")
    public TaskDTO create(@PathVariable Long userId, @Validated(OnCreate.class) @RequestBody TaskDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task, userId);
        return taskMapper.toDto(createdTask);
    }

}
