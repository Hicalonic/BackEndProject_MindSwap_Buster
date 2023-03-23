package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.mapper.ClientMapper;
import org.mindswap.model.Client;
import org.mindswap.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
        Client client = clientMapper.fromCreateDtoToEntity(clientCreateDto);
        clientRepository.save(client);
        return clientMapper.fromEntityToDto(client);
    }

    @Override
    public List<ClientDto> createClients(List<ClientCreateDto> clientCreateDtoList) {
        List<Client> clientList = clientCreateDtoList.stream().map(c->clientMapper.fromCreateDtoToEntity(c)).toList();
        clientList.forEach(c -> clientRepository.save(c));
        return clientList.stream().map(c->clientMapper.fromEntityToDto(c)).toList();

    }

    @Override
    public ClientDto getClientInfo(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        return clientMapper.fromEntityToDto(client);
    }

    @Override
    public ClientDto getClientById(Long clientId) {
       Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        return clientMapper.fromEntityToDto(client);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(c->clientMapper.fromEntityToDto(c)).toList();
    }


    @Override
    public ClientDto updateClient(Long clientId, ClientUpdateDto clientUpdateDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

       if(clientUpdateDto.getFirstName() != null) {
           client.setFirstName(clientUpdateDto.getFirstName());
       }
       if(clientUpdateDto.getLastName() != null) {
           client.setLastName(clientUpdateDto.getLastName());
       }
       if(clientUpdateDto.getEmail() != null) {
           client.setEmail(clientUpdateDto.getEmail());
       }
       clientRepository.save(client);
       return clientMapper.fromEntityToDto(client);
    }

    @Override
    public ClientDto updatePassword(Long clientId, UpdatePasswordDto updatePasswordDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

        if(updatePasswordDto.getPassword().equals(client.getPassword())) {
            client.setPassword(updatePasswordDto.getNewPassword());
        }
        clientRepository.save(client);
        return clientMapper.fromEntityToDto(client);
    }


    @Override
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(client);
    }



}
