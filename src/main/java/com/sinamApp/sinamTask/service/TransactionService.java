package com.sinamApp.sinamTask.service;

import com.sinamApp.sinamTask.dto.TransactionDTO;
import com.sinamApp.sinamTask.dto.TransactionDtoSender;

import java.util.HashSet;

public interface TransactionService {
    void makeTransaction(TransactionDtoSender transactionDTO);
    HashSet<TransactionDTO> getAllTransactionByAccount(Long userId);
    HashSet<TransactionDTO> getSendTransactionByAccount(Long userId);
    HashSet<TransactionDTO> getAddresseeTransactionByAccount(Long userId);
}
