package com.Estudo.todo.exception;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(TaskNotFaundException.class)
    public ResponseEntity<RestErrorMessageString> taskNotFaundHandler (TaskNotFaundException exception){
        RestErrorMessageString threatResponse = new RestErrorMessageString(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(UserNotFaundException.class)
    public ResponseEntity<RestErrorMessageString> userNotFaundHandler (UserNotFaundException exception){
        RestErrorMessageString threatResponse = new RestErrorMessageString(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandler (MethodArgumentNotValidException exception){

        List<String>errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrorMessageList<>(HttpStatus.BAD_REQUEST, errors));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestErrorMessageString> invalidCredentialsHandler(BadCredentialsException exception) {
        RestErrorMessageString response = new RestErrorMessageString(HttpStatus.UNAUTHORIZED, "Credenciais invalidos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
