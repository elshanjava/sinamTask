package com.sinamApp.sinamTask.mapper;

import com.sinamApp.sinamTask.dto.UserDTO;
import com.sinamApp.sinamTask.model.Role;
import com.sinamApp.sinamTask.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

@Mapper
public interface UserMapper  {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "roles", constant = "USER")
    UserDTO UserEntityToDTO(UserEntity userEntity);

    @Mapping(target = "roles", ignore = true)
    UserEntity UserDtoToEntity(UserDTO userDTO);


}
