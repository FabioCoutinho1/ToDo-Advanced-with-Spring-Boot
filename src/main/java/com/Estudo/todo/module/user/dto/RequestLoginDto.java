package com.Estudo.todo.module.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestLoginDto(
        @NotBlank(message = "Nome de usuario e Obrigatorio")
        String userName,
        @NotBlank(message = "Senha e Obrigatória")
        String password
) {
}
