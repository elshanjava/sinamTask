package com.sinamApp.sinamTask.service.impl;

import com.sinamApp.sinamTask.dto.UserDTO;
import com.sinamApp.sinamTask.exception.ExistUserException;
import com.sinamApp.sinamTask.mapper.UserMapper;
import com.sinamApp.sinamTask.model.UserEntity;
import com.sinamApp.sinamTask.repository.UserRepository;
import com.sinamApp.sinamTask.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepo;

    public RegistrationServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity addUser( UserDTO user){
        List<UserEntity> allUsers = userRepo.findAll();
        for (UserEntity allUser : allUsers) {
            if (allUser.getEmail().equals(user.getEmail())) {
                log.info("User with email:" + user.getEmail() + " want to register with exist email ");
                throw new ExistUserException();
            }
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setActive(true);
        user.setPassword(encodedPassword);
        userRepo.save(UserMapper.INSTANCE.UserDtoToEntity(user));
        log.info("User with email:" +user.getEmail()+" was register");
        return new ResponseEntity(HttpStatus.OK);
    }
}
