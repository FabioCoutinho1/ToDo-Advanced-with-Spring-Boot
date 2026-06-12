package com.Estudo.todo.module.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCreatNewTaskDto (
        @NotBlank(message = "O nome da tarefa é obrigatório")
        @Size(min = 3, max = 255)
        String name
){
}
