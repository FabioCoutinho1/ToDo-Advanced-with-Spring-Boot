package com.Estudo.todo.module.user.dto;

import com.Estudo.todo.module.user.userRole.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RequestRegisterDto(
        @NotBlank(message = "Nome de usuario e Obrigatorio")
        String userName,
        @NotBlank(message = "Senha e Obrigatória")
        String password
){
}
