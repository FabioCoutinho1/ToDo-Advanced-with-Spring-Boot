package com.Estudo.todo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {
    private HttpStatus httpStatus;
    private String message;
}
