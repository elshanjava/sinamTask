package com.sinamApp.sinamTask.mapper;

import com.sinamApp.sinamTask.dto.TransactionDTO;
import com.sinamApp.sinamTask.dto.TransactionDtoSender;
import com.sinamApp.sinamTask.model.AccountEntity;
import com.sinamApp.sinamTask.model.TransactionEntity;
import com.sinamApp.sinamTask.repository.AccountRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper{

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO transactionEntityToDto(TransactionEntity transactionEntity);


}
