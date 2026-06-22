package com.estudo.todo.module.task.dto;

import jakarta.validation.constraints.Size;

import java.util.Optional;

public record RequestUpdateTaskDto(
        @Size(min = 3, max = 255)
        Optional<String> name,
        Optional<Boolean> done,
        Optional<Boolean> favorite
) {
}
