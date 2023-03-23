package org.mindswap.mappersUser;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dtosUser.*;
import org.mindswap.model.User;

@Mapper(componentModel = "spring")
public interface UserManagerMapper {

    User fromDtoToEntity(UserManagerDto userManagerDto);
    @InheritInverseConfiguration
    UserManagerDto fromEntityToDto(User user);
    User fromCreateDtoToEntity(UserManagerCreateDto userManagerCreateDto);
    @InheritInverseConfiguration
    UserManagerCreateDto fromEntityToCreateDto(User user);
    User fromUpdateDtoToEntity(UserManagerUpdateDto userManagerUpdateDto);
    @InheritInverseConfiguration
    UserManagerUpdateDto fromEntityToUpdateDto(User user);
}
