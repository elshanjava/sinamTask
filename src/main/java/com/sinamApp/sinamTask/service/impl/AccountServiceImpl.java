package com.sinamApp.sinamTask.service.impl;

import com.sinamApp.sinamTask.dto.AccountDTO;
import com.sinamApp.sinamTask.dto.UserDTO;
import com.sinamApp.sinamTask.exception.NoExistElementException;
import com.sinamApp.sinamTask.exception.NoExistUserException;
import com.sinamApp.sinamTask.mapper.AccountMapper;
import com.sinamApp.sinamTask.mapper.UserMapper;
import com.sinamApp.sinamTask.model.AccountEntity;
import com.sinamApp.sinamTask.model.AccountStatuses;
import com.sinamApp.sinamTask.model.Currencies;
import com.sinamApp.sinamTask.repository.AccountRepository;
import com.sinamApp.sinamTask.repository.UserRepository;
import com.sinamApp.sinamTask.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AccountDTO> getAllAccountsByUserId(Long userId){
        final List<AccountDTO> accountsList = new ArrayList<>();
        try {
            accountRepository.findAllByUserId(userRepository.findById(userId).get()).forEach(acc ->
                accountsList.add(AccountMapper.INSTANCE.accountEntityToDTO(acc)));
            if (accountsList.isEmpty()) {
                log.info("User with id: " + userId +" do not have any accounts");
                throw new NoExistElementException();
            }
            log.info("User with id: " + userId + " get all accounts");
        } catch (Exception e) {
           throw new NoExistUserException();
        }
        return accountsList;
    }

    @Override
    public AccountDTO getAccountInfoById(Long accountId){
        AccountDTO accountInfo = null;
        try {
            accountInfo = AccountMapper.INSTANCE.accountEntityToDTO(accountRepository.findById(accountId).get());
        } catch (Exception e) {
            log.info("User do not have account with id: " +accountId);
            throw new NoExistElementException();
        }
        log.info("User get information about account with id: " + accountId);
        return accountInfo;
    }

    @Override
    public void addNewAccount(Long userId){
        UserDTO userFromDB = null;
        try {
            userFromDB = UserMapper.INSTANCE.UserEntityToDTO(userRepository.findById(userId).get());
            if (userFromDB == null){
                log.info("User with id: " + userId +" do not found in DB");
            }
        } catch (Exception e) {
            throw new NoExistUserException();
        }
        Long newAccountNumber = generated();

        AccountDTO newAddAccount = null;
            System.out.println(userFromDB);
            newAddAccount = new AccountDTO();
            newAddAccount.setAccountNumber(newAccountNumber);
            newAddAccount.setBalance(300.0);
            newAddAccount.setCurrency(Currencies.AZN);
            newAddAccount.setStatus(AccountStatuses.DEACTIVE);
            newAddAccount.setUserId(UserMapper.INSTANCE.UserDtoToEntity(userFromDB));
            System.out.println(newAddAccount);
            log.info("User with id: " + userId +" add new account with number: " +newAccountNumber);

        accountRepository.save(AccountMapper.INSTANCE.accountDTOToEntity(newAddAccount));
    }


    @Override
    public void deleteAccount(Long accountId){
        try {
            log.info("Account with id: " + accountId +" was delete");
            accountRepository.deleteById(accountId);
        } catch (Exception e) {
            log.info("Account with id: " + accountId +" do not fount in DB");
            throw new NoExistElementException();
        }
    }

    @Override
    public void changeAccountStatusToActive(Long accountId){
        try {
            AccountEntity changeAccount= accountRepository.findById(accountId).get();
            changeAccount.setStatus(AccountStatuses.ACTIVE);
            accountRepository.save(changeAccount);
            log.info("Account with id: " + accountId +" have status active");
        } catch (Exception e) {
            throw new NoExistElementException();
        }
    }

    @Override
    public List<AccountDTO> getAllActiveAccountByEmail(String email){
        List<AccountDTO> accountListByEmail = new ArrayList<>();
        userRepository.findAll().forEach(u->
        {   if (u.getEmail().contains(email)){
                accountRepository.findAllByUserId(u).forEach(acc->{
                    if (acc.getStatus()==AccountStatuses.ACTIVE){
                        accountListByEmail.add(AccountMapper.INSTANCE.accountEntityToDTO(acc));
                    }
                });
            }
        });
        if (accountListByEmail.isEmpty()){
            log.info("User with mail: " + email + " do not have active account");
            throw new NoExistElementException();
        }
        return accountListByEmail;
    }

    @Override
    public Long generated (){
        Random random = new Random();
        Long newAccountNumber = Math.abs(random.nextLong());
        List<AccountEntity> allAccounts = accountRepository.findAll();
        allAccounts.forEach(acc-> {
            if (newAccountNumber.equals(acc.getAccountNumber())){
                generated();
            }
        });
        return newAccountNumber;
    }



}
