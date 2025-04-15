package com.edstry.Tasklist.web.mappers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDTO> {

}
