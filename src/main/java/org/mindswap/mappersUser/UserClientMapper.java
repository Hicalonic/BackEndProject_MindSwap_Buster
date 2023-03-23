package org.mindswap.mappersUser;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.ClientCreateDto;
import org.mindswap.dto.ClientDto;
import org.mindswap.dto.ClientUpdateDto;
import org.mindswap.dtosUser.UserClientCreateDto;
import org.mindswap.dtosUser.UserClientDto;
import org.mindswap.dtosUser.UserClientUpdateDto;
import org.mindswap.model.Client;
import org.mindswap.model.User;

@Mapper(componentModel = "spring")
public interface UserClientMapper {

    User fromDtoToEntity(UserClientDto userClientDto);
    @InheritInverseConfiguration
    UserClientDto fromEntityToDto(User user);
    User fromCreateDtoToEntity(UserClientCreateDto userClientCreateDto);
    @InheritInverseConfiguration
    UserClientCreateDto fromEntityToCreateDto(User user);
    User fromUpdateDtoToEntity(UserClientUpdateDto userClientUpdateDto);
    @InheritInverseConfiguration
    UserClientUpdateDto fromEntityToUpdateDto(User user);
}
