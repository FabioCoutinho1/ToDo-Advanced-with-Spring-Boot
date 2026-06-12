package com.Estudo.todo.module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestLoginDto(
        @NotBlank(message = "Nome de usuario e Obrigatorio")
        String userName,
        @NotBlank(message = "Senha e Obrigatória")
        @Size(min = 6, max = 50)
        String password
) {
}
