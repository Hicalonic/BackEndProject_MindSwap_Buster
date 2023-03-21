package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.mapper.ClientMapper;
import org.mindswap.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }


    @Override
    public ClientDto createClient(ClientCreateDto clientCreateDto) {
        return null;
    }

    @Override
    public List<ClientDto> createClients(List<ClientCreateDto> clientCreateDtoList) {
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
