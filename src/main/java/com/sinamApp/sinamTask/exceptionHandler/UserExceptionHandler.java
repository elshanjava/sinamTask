package com.sinamApp.sinamTask.exceptionHandler;

import com.sinamApp.sinamTask.exception.ExistUserException;
import com.sinamApp.sinamTask.exception.NoExistUserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoExistUserException.class)
    protected ResponseEntity<MyAccountException> handlerNoSuchElement(){
        return new ResponseEntity<>(new MyAccountException("There is no such user"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistUserException.class)
    protected ResponseEntity<MyAccountException> handlerExistUser(){
        return new ResponseEntity<>(new MyAccountException("User with this mail is exist"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class MyAccountException{
        private String message;
    }
}
