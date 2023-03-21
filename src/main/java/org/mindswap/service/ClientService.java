package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.model.Movie;

import java.util.List;

public interface ClientService {

    ClientDto createClient(ClientDto clientDto);
    List<ClientDto> createClients(List<ClientDto> clientDtoList);
    ClientDto getClientInfo(Long clientId);

    ClientDto getClientById(Long clientId);

    List<ClientDto> getAllClients();

    ClientDto updateClient(Long clientId, ClientUpdateDto clientUpdateDto);

    public void deleteClient(Long clientId);















}
