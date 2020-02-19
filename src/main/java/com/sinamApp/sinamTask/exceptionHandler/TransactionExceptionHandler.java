package com.sinamApp.sinamTask.exceptionHandler;

import com.sinamApp.sinamTask.exception.TransactionException;
import com.sinamApp.sinamTask.exception.TransactionNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    protected ResponseEntity<MyAccountException> handlerEnoughBalance(){
        return new ResponseEntity<>(new MyAccountException("Account do not enough balance"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    protected ResponseEntity<MyAccountException> handlerExistTransaction(){
        return new ResponseEntity<>(new MyAccountException("Transaction not found"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class MyAccountException{
        private String message;
    }
}
