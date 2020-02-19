package com.sinamApp.sinamTask.service;

import com.sinamApp.sinamTask.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccountsByUserId(Long userId);
    AccountDTO getAccountInfoById(Long accountId);
    void addNewAccount(Long accountDTO);
    void deleteAccount(Long accountId);
    List<AccountDTO> getAllActiveAccountByEmail(String email);
    void changeAccountStatusToActive(Long accountId);
    Long generated ();

}
