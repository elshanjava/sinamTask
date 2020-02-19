package com.sinamApp.sinamTask.dto;

import com.sinamApp.sinamTask.model.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private AccountEntity sender;
    private AccountEntity addressee;
    private Double amount;
}
