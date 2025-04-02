package com.edstry.Tasklist.repository;

import com.edstry.Tasklist.domain.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> findById(Long id);
    List<Task> findAllByUserId(Long userId);
    void assignToUserById(Long taskId, Long userId);
    void update(Task task);
    void delete(Long id);
    void create(Task task);
}
