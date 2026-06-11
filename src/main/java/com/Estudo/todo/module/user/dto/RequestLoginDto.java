package com.Estudo.todo.module.user.dto;

import jakarta.validation.constraints.NotNull;

public record RequestLoginDto(
        @NotNull
        String userName,
        @NotNull
        String password
) {
}
