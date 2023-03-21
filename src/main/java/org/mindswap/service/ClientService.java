package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.model.Movie;

import java.util.List;

public interface ClientService {

    ClientDto createClient(ClientCreateDto clientCreateDto);
    List<ClientDto> createClients(List<ClientCreateDto> clientCreateDtoList);
    ClientDto getClientInfo(Long clientId);

    ClientDto getClientById(Long clientId);

    List<ClientDto> getAllClients();

    ClientDto updateClient(Long clientId, ClientUpdateDto clientUpdateDto);

    public void deleteClient(Long clientId);















}
