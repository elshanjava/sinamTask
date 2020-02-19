package com.sinamApp.sinamTask.controller;

import com.sinamApp.sinamTask.dto.AccountDTO;
import com.sinamApp.sinamTask.service.impl.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/account")
@RestController
public class AccountController {
    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/{userId}")
    public List<AccountDTO> getAllAccountsByUserId(@PathVariable(name = "userId") Long userId){
        return accountService.getAllAccountsByUserId(userId);
    }

    @GetMapping(path = "/info/{accountId}")
    public AccountDTO getAccountInfoById(@PathVariable(name = "accountId") Long accountId){
        return accountService.getAccountInfoById(accountId);
    }

    @PostMapping(path = "/{userId}")
    public void addNewAccount(@PathVariable (name = "userId")Long userId){
         accountService.addNewAccount(userId);
    }

    @DeleteMapping(path = "/{accountId}")
    public void deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);

    }

    @PutMapping(path = "/activate/{accountId}")
    public void changeAccountStatusToActive(@PathVariable Long accountId){
        accountService.changeAccountStatusToActive(accountId);
    }

    @GetMapping(path = "/email/{email}")
    public List<AccountDTO> getAllActiveAccountByEmail(@PathVariable String email) {
        return accountService.getAllActiveAccountByEmail(email);
    }
}
