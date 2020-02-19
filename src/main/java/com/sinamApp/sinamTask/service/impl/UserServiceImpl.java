package com.sinamApp.sinamTask.service.impl;

import com.sinamApp.sinamTask.dto.UserDTO;
import com.sinamApp.sinamTask.exception.NoExistUserException;
import com.sinamApp.sinamTask.mapper.UserMapper;
import com.sinamApp.sinamTask.model.UserEntity;
import com.sinamApp.sinamTask.repository.UserRepository;
import com.sinamApp.sinamTask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserInfo(Long userId){
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findById(userId).get();
        } catch (Exception e) {
            log.info("User with id: " + userId + "not exist");
            throw new NoExistUserException();
        }

        log.info("User with id: " + userId + "get information about profile");
        return UserMapper.INSTANCE.UserEntityToDTO(userEntity);
    }


    
}
