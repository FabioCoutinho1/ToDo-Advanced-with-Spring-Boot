package com.Estudo.todo.module.user.dto;

import com.Estudo.todo.module.user.userRole.UserRole;

public record RequestRegisterDto(String userName, String password, UserRole role) {
}
