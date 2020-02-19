package com.sinamApp.sinamTask.controller;

import com.sinamApp.sinamTask.configuration.jwt.JwtTokenProvider;
import com.sinamApp.sinamTask.model.UserEntity;
import com.sinamApp.sinamTask.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/auth/")
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserEntity requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
            UserEntity user = userRepository.findByEmail(email);

            if (user == null) {
                log.info("User with email: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);
            log.info("User - " + email +" was login");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("Invalid username or password");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

}