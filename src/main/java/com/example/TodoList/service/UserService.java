package com.example.TodoList.service;

import com.example.TodoList.persist.entity.User;
import com.example.TodoList.persist.repo.UserRepository;
import com.example.TodoList.repr.UserRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.example.TodoList.security.Utils.getCurrentUser;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserRepr userRepr) {
        User user = new User();
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        userRepository.save(user);
    }

    public Optional<Long> getCurrentUserId() {
        return getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .map(User::getId);
    }
}