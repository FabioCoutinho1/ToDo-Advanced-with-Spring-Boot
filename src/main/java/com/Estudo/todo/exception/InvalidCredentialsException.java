package com.Estudo.todo.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
    public InvalidCredentialsException() {
        super("Credenciais invalidas");
    }
}
