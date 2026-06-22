package com.estudo.todo.module.task.service;

import com.estudo.todo.exception.TaskNotFaundException;
import com.estudo.todo.module.task.dto.RequestCreatNewTaskDto;
import com.estudo.todo.module.task.dto.RequestUpdateTaskDto;
import com.estudo.todo.module.task.dto.ResponseTaskDto;
import com.estudo.todo.module.task.entity.Task;
import com.estudo.todo.module.task.mapper.ResponseTaskMapper;
import com.estudo.todo.module.task.repository.TaskRepository;
import com.estudo.todo.module.user.entity.User;
import com.estudo.todo.module.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    private Task findTaskOrThrwo(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFaundException());
    }

    private Long getCurrentUserId() {
        User user = (User) (Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication())).getPrincipal();
        if (user == null) {
            throw new RuntimeException("User not found in security context");
        }
        return user.getId();
    }


    public List<ResponseTaskDto> getAllTasks() {

        return taskRepository.findByUserId(this.getCurrentUserId()).stream().map(task -> ResponseTaskMapper.toResponse(task)).toList();
    }

    public ResponseTaskDto getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFaundException());

        if (task.getUser().getId() != this.getCurrentUserId()) throw new TaskNotFaundException();

        return ResponseTaskMapper.toResponse(task);

    }

    public ResponseTaskDto createTask(RequestCreatNewTaskDto task) {
        Task taskEntity = new Task();
        taskEntity.setName(task.name());
        taskEntity.setUser(userRepository.findById(this.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        taskEntity.setCreate_at(LocalDateTime.now());


        Task newTask = taskRepository.save(taskEntity);
        System.out.println(newTask);

        return ResponseTaskMapper.toResponse(newTask);
    }

    @Transactional
    public ResponseTaskDto updateTask(Long id, RequestUpdateTaskDto task) {
        Task taskEntity = this.findTaskOrThrwo(id);

        task.name().ifPresent(taskEntity::setName);
        task.done().ifPresent(taskEntity::setDone);
        task.favorite().ifPresent(taskEntity::setFavorite);

        return ResponseTaskMapper.toResponse(taskEntity);
    }

    public void deleteTask(long id) {
        this.findTaskOrThrwo(id);
        taskRepository.deleteById(id);
    }
}
