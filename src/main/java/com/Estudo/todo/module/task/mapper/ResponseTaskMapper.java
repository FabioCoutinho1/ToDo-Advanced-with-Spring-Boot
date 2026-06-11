package com.Estudo.todo.module.task.mapper;

import com.Estudo.todo.module.task.dto.ResponseTaskDto;
import com.Estudo.todo.module.task.entity.Task;

public class ResponseTaskMapper {

    public static ResponseTaskDto toResponse(Task task){
        return new ResponseTaskDto(
            task.getId(),
            task.getName(),
            task.getDone(),
            task.getFavorite(),
            task.getCreate_at()
        );
    }
}
