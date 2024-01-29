package com.example.TodoList.persist.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.TodoList.persist.entity.ToDo;
import com.example.TodoList.repr.ToDoRepr;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    @Query("select new com.example.TodoList.repr.ToDoRepr(t.id, t.description, t.user.username, t.targetDate) " +
            "from com.example.TodoList.persist.entity.ToDo t" +
            "where t.user.id = :userId")
    List<ToDoRepr> findToDosByUserId(@Param("userId") Long userId);
}