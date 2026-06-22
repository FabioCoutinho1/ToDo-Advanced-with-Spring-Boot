package com.estudo.todo.module.task.dto;

import java.time.LocalDateTime;

public record ResponseTaskDto (
    long id,
    String name,
    boolean done,
    boolean favorite,
    LocalDateTime createAt
) {
    
}
