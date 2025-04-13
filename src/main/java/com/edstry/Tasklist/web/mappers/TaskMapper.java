package com.edstry.Tasklist.web.mappers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    //@BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    TaskDTO toDto(Task task);
    //@BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    List<TaskDTO> toDto(List<Task> tasks);
    //@BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Task toEntity(TaskDTO taskDTO);
}
