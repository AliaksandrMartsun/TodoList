package com.example.TodoList.repr;

import com.example.TodoList.persist.entity.ToDo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ToDoRepr {

    private Long id;

    @NotEmpty
    private String description;

    private String username;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    public ToDoRepr() {
    }

    public ToDoRepr(ToDo toDo) {
        this.id = toDo.getId();
        this.description = toDo.getDescription();
        this.targetDate = toDo.getTargetDate();
        this.username = toDo.getUser().getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}