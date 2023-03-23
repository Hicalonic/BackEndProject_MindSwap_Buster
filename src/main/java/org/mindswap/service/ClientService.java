package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.dtosUser.UserClientCreateDto;
import org.mindswap.dtosUser.UserClientDto;
import org.mindswap.dtosUser.UserClientUpdateDto;
import org.mindswap.model.Movie;

import java.util.List;

public interface ClientService {

    UserClientDto createClient(UserClientCreateDto userClientCreateDto);
    List<UserClientDto> createClients(List<UserClientCreateDto> clientCreateDtoList);
    UserClientDto getClientInfo(Long clientId);

    UserClientDto getClientById(Long clientId);

    List<UserClientDto> getAllClients();

    UserClientDto updateClient(Long clientId, UserClientUpdateDto userClientUpdateDto);

    UserClientDto updatePassword(Long clientId, UpdatePasswordDto updatePasswordDto);

    void updateClientRole(Long clientId, RoleUpdateDto roleUpdateDto);

    void deleteClient(Long clientId);















}
