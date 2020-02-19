package com.sinamApp.sinamTask.configuration.jwt;

import com.sinamApp.sinamTask.model.UserEntity;
import com.sinamApp.sinamTask.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


    @Service
    @Slf4j
    public class JwtUserDetailsService implements UserDetailsService {

        private final UserRepository userService;

        @Autowired
        public JwtUserDetailsService(UserRepository userService) {
            this.userService = userService;
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            UserEntity user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            JwtUser jwtUser = JwtUserFactory.create(user);
            log.info("IN loadUserByUsername - user with email: {} successfully loaded", email);
            return jwtUser;
        }
    }

