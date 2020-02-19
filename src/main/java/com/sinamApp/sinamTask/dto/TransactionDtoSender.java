package com.sinamApp.sinamTask.dto;

import com.sinamApp.sinamTask.model.TransactionEntity;
import com.sinamApp.sinamTask.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class TransactionDtoSender {

    private Long id;
    private Long sender;
    private Long addressee;
    private Double amount;

    public static TransactionEntity transactionDtoToEntity(TransactionDtoSender dtoSender, AccountRepository accountRepository) {

        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setId(dtoSender.getId());
        newTransaction.setSender(accountRepository.findByAccountNumber(dtoSender.getSender()));
        newTransaction.setAddressee(accountRepository.findByAccountNumber(dtoSender.getAddressee()));
        newTransaction.setAmount(dtoSender.getAmount());
        return newTransaction;
    }
}
