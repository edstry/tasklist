package com.edstry.Tasklist.repository.impl;

import com.edstry.Tasklist.domain.exception.ResourceMappingException;
import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.repository.DataSourceConfig;
import com.edstry.Tasklist.repository.TaskRepository;
import com.edstry.Tasklist.repository.mappers.TaskRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    @Override
    public Optional<Task> findById(Long id) {

        String FIND_BY_ID = """
                    SELECT t.id              as task_id,
                           t.title           as task_title,
                           t.description     as task_description,
                           t.status          as task_status,
                           t.expiration_date as task_expiration_date
                    FROM tasks t
                    WHERE id = ?
                    """;
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);

            try(ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while finding task by user id.");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {

        String FIND_ALL_BY_USER_ID = """
                    SELECT t.id              as task_id,
                           t.title           as task_title,
                           t.description     as task_description,
                           t.status          as task_status,
                           t.expiration_date as task_expiration_date
                    FROM tasks t
                             JOIN users_tasks ut on t.id = ut.task_id
                    WHERE ut.user_id = ?
                    """;
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);

            try(ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while finding task list by user id.");
        }
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

        String ASSIGN = "INSERT INTO users_tasks (user_id, task_id) VALUES (?, ?)";
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while assigning to user.");
        }
    }

    @Override
    public void update(Task task) {

        String UPDATE = """
                    UPDATE tasks
                    SET title = ?,
                        description = ?,
                        expiration_date = ?,
                        status = ?
                    WHERE id = ?
                    """;
        try {
            Connection connection = dataSourceConfig.getConnection();

            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());
            if(task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR); // устанавливаем null в таблицу
            } else {
                statement.setString(2, task.getDescription());
            }
            if(task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }

            statement.setString(4, task.getStatus().name());
            statement.setLong(5, task.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while updating task.");
        }
    }

    @Override
    public void create(Task task) {

        String CREATE = """
                    INSERT INTO tasks(title, description, status, expiration_date)
                    VALUES (?, ?, ?, ?)
                    """;
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());

            if(task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR); // устанавливаем null в таблицу
            } else {
                statement.setString(2, task.getDescription());
            }

            statement.setString(3, task.getStatus().name());

            if(task.getExpirationDate() == null) {
                statement.setNull(4, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(4, Timestamp.valueOf(task.getExpirationDate()));
            }

            statement.executeUpdate();

            try(ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                task.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while creating task.");
        }
    }

    @Override
    public void delete(Long id) {

        String DELETE = "DELETE FROM tasks WHERE id = ?";
        try {
            Connection connection = dataSourceConfig.getConnection();

            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while deleting task.");
        }
    }
}
