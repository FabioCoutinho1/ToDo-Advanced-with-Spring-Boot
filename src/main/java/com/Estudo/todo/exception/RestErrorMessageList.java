package com.Estudo.todo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessageList<T> {
    private HttpStatus httpStatus;
    private List<T> message;
}
