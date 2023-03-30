package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.*;
import org.mindswap.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User fromDtoToEntity(UserDto userDto);
    @InheritInverseConfiguration
    UserDto fromEntityToDto(User user);
    User fromCreateDtoToEntity(UserCreateDto userCreateDto);
    @InheritInverseConfiguration
    UserCreateDto fromEntityToCreateDto(User user);
    User fromUpdateDtoToEntity(UserUpdateDto userUpdateDto);
    @InheritInverseConfiguration
    UserUpdateDto fromEntityToUpdateDto(User user);
}
