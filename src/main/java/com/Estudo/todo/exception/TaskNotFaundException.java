package com.Estudo.todo.exception;

public class TaskNotFaundException extends RuntimeException{

    public TaskNotFaundException() {super("Tarefa não encontrado");}

    public TaskNotFaundException(String message){super(message);}
}
