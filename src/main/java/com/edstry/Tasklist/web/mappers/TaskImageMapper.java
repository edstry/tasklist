package com.edstry.Tasklist.web.mappers;

import com.edstry.Tasklist.domain.task.Task;
import com.edstry.Tasklist.domain.task.TaskImage;
import com.edstry.Tasklist.web.dto.task.TaskDTO;
import com.edstry.Tasklist.web.dto.task.TaskImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDTO> {

}
