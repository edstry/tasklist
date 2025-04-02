package com.edstry.Tasklist.service;

import com.edstry.Tasklist.domain.task.Task;

import java.util.List;

public interface TaskService {

    Task getById(Long id);
    List<Task> getAllByUserId(Long userId);
    Task update(Task task);
    Task create(Task task, Long userId);
    void delete(Long id);
}
