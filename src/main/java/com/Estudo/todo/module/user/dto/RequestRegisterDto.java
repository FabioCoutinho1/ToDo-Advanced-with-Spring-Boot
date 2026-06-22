package com.estudo.todo.module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestRegisterDto(
        @NotBlank(message = "Nome de usuario e Obrigatorio")
        @Size(min = 3, max = 255)
        String userName,
        @NotBlank(message = "Senha e Obrigatória")
        @Size(min = 6, max = 50)
        String password
) {
}
