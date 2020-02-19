package com.sinamApp.sinamTask.controller;

import com.sinamApp.sinamTask.dto.UserDTO;
import com.sinamApp.sinamTask.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/v1/user/")
@RestController
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{userId}")
    public UserDTO getUserInfo(@PathVariable (name = "userId") Long userId){
        return userService.getUserInfo(userId);
    }

}