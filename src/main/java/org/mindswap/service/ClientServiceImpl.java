package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.dtosUser.UserClientCreateDto;
import org.mindswap.dtosUser.UserClientDto;
import org.mindswap.dtosUser.UserClientUpdateDto;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.mapper.ClientMapper;
import org.mindswap.mappersUser.UserClientMapper;
import org.mindswap.model.Client;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private UserRepository userRepository;
    private UserClientMapper userClientMapper;

    @Autowired
    public ClientServiceImpl(UserRepository userRepository, UserClientMapper userClientMapper) {
        this.userRepository = userRepository;
        this.userClientMapper = userClientMapper;
    }


    @Override
    public UserClientDto createClient(UserClientCreateDto userClientCreateDto) {
        User user = userClientMapper.fromCreateDtoToEntity(userClientCreateDto);
        userRepository.save(user);
        return userClientMapper.fromEntityToDto(user);
    }

    @Override
    public List<UserClientDto> createClients(List<UserClientCreateDto> clientCreateDtoList) {
        List<User> clientList = clientCreateDtoList.stream().map(c->userClientMapper.fromCreateDtoToEntity(c)).toList();
        clientList.forEach(c -> userRepository.save(c));
        return clientList.stream().map(c->userClientMapper.fromEntityToDto(c)).toList();

    }

    @Override
    public UserClientDto getClientInfo(Long clientId) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        return userClientMapper.fromEntityToDto(user);
    }

    @Override
    public UserClientDto getClientById(Long clientId) {
       User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        return userClientMapper.fromEntityToDto(user);
    }

    @Override
    public List<UserClientDto> getAllClients() {
        return userRepository.findAll().stream().map(c->userClientMapper.fromEntityToDto(c)).toList();
    }


    @Override
    public UserClientDto updateClient(Long clientId, UserClientUpdateDto userClientUpdateDto) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

       if(userClientUpdateDto.getFirstName() != null) {
           user.setFirstName(userClientUpdateDto.getFirstName());
       }
       if(userClientUpdateDto.getLastName() != null) {
           user.setLastName(userClientUpdateDto.getLastName());
       }
       if(userClientUpdateDto.getEmail() != null) {
           user.setEmail(userClientUpdateDto.getEmail());
       }

       userRepository.save(user);
       return userClientMapper.fromEntityToDto(user);
    }

    @Override
    public UserClientDto updatePassword(Long clientId, UpdatePasswordDto updatePasswordDto) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        if(updatePasswordDto.getPassword().equals(user.getPassword())) {
            user.setPassword(updatePasswordDto.getNewPassword());
        }
        userRepository.save(user);
        return userClientMapper.fromEntityToDto(user);
    }

    @Override
    public void updateClientRole(Long clientId, RoleUpdateDto roleUpdateDto) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        if(roleUpdateDto.getRole() != null) {
            user.setRole(roleUpdateDto.getRole());
        }
        userRepository.save(user);
    }


    @Override
    public void deleteClient(Long clientId) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        userRepository.delete(user);
    }



}
