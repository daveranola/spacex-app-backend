package com.example.spacex_backend.controller;

import com.example.spacex_backend.exception.EmailAlreadyUsedException;
import com.example.spacex_backend.model.User;
import com.example.spacex_backend.repository.UserRepository;
import com.example.spacex_backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/addUser") //any path with addUser, will add user to mysql
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "User added successfully";
    }
}
