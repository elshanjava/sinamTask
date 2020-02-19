package com.sinamApp.sinamTask.service;

import com.sinamApp.sinamTask.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity addUser(UserDTO user);
}
