package com.estudo.todo.module.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.estudo.todo.module.user.entity.User;

@Getter
@Setter

@Entity
@Table(name = "Tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean done = false;

    @Column(nullable = false)
    private Boolean favorite = false;

    private LocalDateTime create_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(){}

}
