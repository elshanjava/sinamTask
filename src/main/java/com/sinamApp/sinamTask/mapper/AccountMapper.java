package com.sinamApp.sinamTask.mapper;

import com.sinamApp.sinamTask.dto.AccountDTO;
import com.sinamApp.sinamTask.model.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    AccountDTO accountEntityToDTO (AccountEntity accountEntity);
    AccountEntity accountDTOToEntity (AccountDTO accountDTO);
}
