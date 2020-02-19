package com.sinamApp.sinamTask.service.impl;

import com.sinamApp.sinamTask.dto.TransactionDTO;
import com.sinamApp.sinamTask.dto.TransactionDtoSender;
import com.sinamApp.sinamTask.exception.NoExistElementException;
import com.sinamApp.sinamTask.exception.TransactionException;
import com.sinamApp.sinamTask.exception.TransactionNotFoundException;
import com.sinamApp.sinamTask.mapper.TransactionMapper;
import com.sinamApp.sinamTask.model.AccountEntity;
import com.sinamApp.sinamTask.model.AccountStatuses;
import com.sinamApp.sinamTask.model.TransactionEntity;
import com.sinamApp.sinamTask.repository.AccountRepository;
import com.sinamApp.sinamTask.repository.TransactionRepository;
import com.sinamApp.sinamTask.repository.UserRepository;
import com.sinamApp.sinamTask.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }



    @Override
    public void makeTransaction(TransactionDtoSender transactionDTO){
        TransactionDtoSender dtoSender = null;
        System.out.println(transactionDTO);
        TransactionEntity tt = dtoSender.transactionDtoToEntity(transactionDTO, accountRepository);
        System.out.println(tt);
            if (accountRepository.findByAccountNumber(transactionDTO.getSender()).getStatus()== AccountStatuses.DEACTIVE){
                log.info("Account: " + transactionDTO.getSender() +" is not active");
                throw new NoExistElementException();
            }else if (accountRepository.findByAccountNumber(transactionDTO.getAddressee()).getStatus()== AccountStatuses.DEACTIVE){
                log.info("Account: " + transactionDTO.getAddressee() +" is not active");
                throw new NoExistElementException();
            }else if (accountRepository.findByAccountNumber(transactionDTO.getSender())
                    .getBalance()<transactionDTO.getAmount()){
                log.info("Account: " + transactionDTO.getSender() +" do not have enough balance");
                throw new TransactionException();
            }else{
                System.out.println(transactionDTO);
                TransactionEntity newTransaction = dtoSender.transactionDtoToEntity(transactionDTO, accountRepository);
                System.out.println(newTransaction);
                AccountEntity senderAccount = accountRepository.findByAccountNumber(transactionDTO.getSender());
                senderAccount.setBalance(senderAccount.getBalance()-transactionDTO.getAmount());
                accountRepository.save(senderAccount);

                AccountEntity addresseeAccount = accountRepository.findByAccountNumber(transactionDTO.getAddressee());
                addresseeAccount.setBalance(addresseeAccount.getBalance()+transactionDTO.getAmount());
                accountRepository.save(addresseeAccount);

                transactionRepository.save(newTransaction);
                log.info("Account: " + transactionDTO.getSender() +" send: " + transactionDTO.getAmount() + " to account: "
                        +transactionDTO.getAddressee());
        }

    }

    @Override
    public HashSet<TransactionDTO> getAllTransactionByAccount(Long userId){
        HashSet<TransactionDTO> actual = new HashSet<>();
        List<TransactionEntity> allTransaction = transactionRepository.findAll();
        List<AccountEntity> allAccountsByUser = accountRepository.findAllByUserId(userRepository.findById(userId).get());
        allAccountsByUser.forEach(acc-> allTransaction.forEach(act->{
            if (act.getSender().getAccountNumber().equals(acc.getAccountNumber()) ||
                    act.getAddressee().getAccountNumber().equals(acc.getAccountNumber())){
                 actual.add(TransactionMapper.INSTANCE.transactionEntityToDto(act));
            }
        }));
        return getTransactionDTOS(userId, actual);
    }

    @Override
    public HashSet<TransactionDTO> getSendTransactionByAccount(Long userId){
        HashSet<TransactionDTO> actual = new HashSet<>();
        List<TransactionEntity> allTransaction = transactionRepository.findAll();
        List<AccountEntity> allAccountsByUser = accountRepository.findAllByUserId(userRepository.findById(userId).get());
        allAccountsByUser.forEach(acc->
                allTransaction.forEach(act->
                {
            if (act.getSender().getAccountNumber().equals(acc.getAccountNumber())){
                 actual.add(TransactionMapper.INSTANCE.transactionEntityToDto(act));
            }
        }));
        return getTransactionDTOS(userId, actual);
    }

    @Override
    public HashSet<TransactionDTO> getAddresseeTransactionByAccount(Long userId){
        HashSet<TransactionDTO> actual = new HashSet<>();
        List<TransactionEntity> allTransaction = transactionRepository.findAll();
        List<AccountEntity> allAccountsByUser = accountRepository.findAllByUserId(userRepository.findById(userId).get());
        allAccountsByUser.forEach(acc -> allTransaction.forEach(act -> {
            if (act.getAddressee().getAccountNumber().equals(acc.getAccountNumber())){
                actual.add(TransactionMapper.INSTANCE.transactionEntityToDto(act));
            }
        }));
        return getTransactionDTOS(userId, actual);
    }


    private HashSet<TransactionDTO> getTransactionDTOS(Long userId, HashSet<TransactionDTO> actual) {
        if (actual.isEmpty()) {
            log.info("User with id: " + userId +" do not have send transactions");
            throw new TransactionNotFoundException();
        }
        log.info("User with id: " + userId +" get send transaction list");
        return actual;
    }

}
