package com.example.TodoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TodoList.persist.entity.ToDo;
import com.example.TodoList.persist.repo.ToDoRepository;
import com.example.TodoList.persist.repo.UserRepository;
import com.example.TodoList.repr.ToDoRepr;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.TodoList.security.Utils.getCurrentUser;

@Service
@Transactional
public class ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public Optional<ToDoRepr> findById(Long id) {
        return toDoRepository.findById(id)
                .map(ToDoRepr::new);
    }

    public List<ToDoRepr> findToDosByUserId(Long userId) {
        return toDoRepository.findToDosByUserId(userId);
    }

    public void save(ToDoRepr toDoRepr) {
        getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .ifPresent(user -> {
                    ToDo toDo = new ToDo();
                    toDo.setId(toDoRepr.getId());
                    toDo.setDescription(toDoRepr.getDescription());
                    toDo.setTargetDate(toDoRepr.getTargetDate());
                    toDo.setUser(user);
                    toDoRepository.save(toDo);
                });
    }

    public void delete(Long id) {
        toDoRepository.findById(id)
                .ifPresent(toDo -> toDoRepository.delete(toDo));
    }
}