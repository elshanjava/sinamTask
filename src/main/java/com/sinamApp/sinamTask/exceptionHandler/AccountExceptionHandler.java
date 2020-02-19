package com.sinamApp.sinamTask.exceptionHandler;

import com.sinamApp.sinamTask.exception.NoExistElementException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoExistElementException.class)
    protected ResponseEntity<MyAccountException> handlerNoSuchElement(){
        return new ResponseEntity<>(new MyAccountException("There is no such account"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class MyAccountException{
        private String message;
    }
}
