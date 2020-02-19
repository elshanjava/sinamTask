package com.sinamApp.sinamTask.dto;

import com.sinamApp.sinamTask.model.AccountStatuses;
import com.sinamApp.sinamTask.model.Currencies;
import com.sinamApp.sinamTask.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private UserEntity userId;
    private Currencies currency;
    private Long accountNumber;
    private Double balance;
    private AccountStatuses status;
}
