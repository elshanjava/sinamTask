package com.sinamApp.sinamTask.controller;

import com.sinamApp.sinamTask.dto.UserDTO;
import com.sinamApp.sinamTask.service.impl.RegistrationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/")
@Controller
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;

    public RegistrationController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("registration")
    public ResponseEntity addUser(@RequestBody UserDTO user){
         return registrationService.addUser(user);
    }
}