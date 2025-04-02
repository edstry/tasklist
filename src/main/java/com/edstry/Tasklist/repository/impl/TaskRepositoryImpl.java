package com.edstry.Tasklist.repository.impl;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.repository.DataSourceConfig;
import com.edstry.Tasklist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
Как работает jdbc пишу для себя.
Вымолняем sql запрос, затем исполняем его с помощью "Statement - PreparedStatement - CallableStatement"
обычный Statement небезопасный он подвержен инекциями
PreparedStatement собирается и вызывается уже с сопоставленными значениями
 */

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;
    private final String FIND_BY_ID =
            """
            SELECT t.id              as task_id,
                   t.title           as task_title,
                   t.description     as task_description,
                   t.status          as task_status,
                   t.expiration_date as task_expiration_date
            FROM tasks t
            WHERE id = ?
            """;

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return List.of();
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void create(Task task) {

    }
}
