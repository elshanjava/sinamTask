package com.sinamApp.sinamTask.controller;

import com.sinamApp.sinamTask.dto.TransactionDTO;
import com.sinamApp.sinamTask.dto.TransactionDtoSender;
import com.sinamApp.sinamTask.service.impl.TransactionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RequestMapping("v1/transaction")
@RestController
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public void makeTransaction(@RequestBody TransactionDtoSender transactionDTO){
        transactionService.makeTransaction(transactionDTO);
    }

    @GetMapping(path = "/all_transactions/{userId}")
    public HashSet<TransactionDTO> getAllTransactionByAccount(@PathVariable Long userId){
        return transactionService.getAllTransactionByAccount(userId);
    }

    @GetMapping(path = "/send_transactions/{userId}")
    public HashSet<TransactionDTO> getSendTransactionByAccount(@PathVariable Long userId){
        return transactionService.getSendTransactionByAccount(userId);
    }

    @GetMapping(path = "/addressee_transactions/{userId}")
    public HashSet<TransactionDTO> getAddresseeTransactionByAccount(@PathVariable Long userId){
        return transactionService.getAddresseeTransactionByAccount(userId);
    }
}
