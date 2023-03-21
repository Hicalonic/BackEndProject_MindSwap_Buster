package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public ClientServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public List<ClientDto> createClients(List<ClientDto> clientDtoList) {
        return null;
    }

    @Override
    public ClientDto getClientInfo(Long clientId) {
        return null;
    }

    @Override
    public ClientDto getClientById(Long clientId) {
        return null;
    }

    @Override
    public List<ClientDto> getAllClients() {
        return null;
    }


    @Override
    public ClientDto updateClient(Long clientId, ClientUpdateDto clientUpdateDto) {
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {

    }



}
