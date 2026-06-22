package com.estudo.todo.module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUserDto(
        @NotBlank
        @Size(min = 3, max = 50)
        String userName
) {
}
