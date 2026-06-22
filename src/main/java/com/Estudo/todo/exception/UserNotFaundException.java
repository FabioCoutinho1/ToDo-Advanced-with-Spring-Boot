package com.estudo.todo.exception;

public class UserNotFaundException extends RuntimeException{

    public UserNotFaundException(){
        super("Usuario não encontrado");
    }

    public UserNotFaundException(String message){super(message);}
}
