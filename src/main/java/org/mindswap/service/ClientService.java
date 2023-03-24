package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.dtosUser.UserClientCreateDto;
import org.mindswap.dtosUser.UserClientDto;
import org.mindswap.dtosUser.UserClientUpdateDto;

import java.util.List;

public interface ClientService {

    UserDto createClient(UserCreateDto userCreateDto);

    List<UserDto> createClients(List<UserCreateDto> userCreateDtos);

    UserDto getClientById(Long clientId);

    List<UserDto> getAllClients();

    UserDto updateClient(Long clientId, UserUpdateDto userUpdateDto);

    UserDto updatePassword(Long clientId, UpdatePasswordDto updatePasswordDto);

    //void updateClientRole(Long clientId, RoleUpdateDto roleUpdateDto);

    void deleteClient(Long clientId);















}
