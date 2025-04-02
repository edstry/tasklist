package com.edstry.Tasklist.web.mappers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDto(Task task);
    List<TaskDTO> toDto(List<Task> tasks);
    Task toEntity(TaskDTO taskDTO);
}
