package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.ClientCreateDto;
import org.mindswap.dto.ClientDto;
import org.mindswap.dto.ClientUpdateDto;
import org.mindswap.model.Client;

@Mapper
public interface ClientMapper {
    Client fromDtoToEntity(ClientDto clientDto);
    @InheritInverseConfiguration
    ClientDto fromEntityToDto(Client client);
    Client fromCreateDtoToEntity(ClientCreateDto clientCreateDto);
    @InheritInverseConfiguration
    ClientCreateDto fromEntityToCreateDto(Client client);
    Client fromUpdateDtoToEntity(ClientUpdateDto clientUpdateDto);
    @InheritInverseConfiguration
    ClientUpdateDto fromEntityToUpdateDto(Client client);


}
