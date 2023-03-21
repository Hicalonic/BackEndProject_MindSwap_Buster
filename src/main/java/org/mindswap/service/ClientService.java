package org.mindswap.service;

import org.mindswap.dto.ClientDto;
import org.mindswap.dto.ClientUpdateDto;

import java.util.List;

public interface ClientService {
    ClientDto getClientById(Long clientId);
    List<ClientDto> getAllClients();
    ClientDto updateClient(Long clientId, ClientUpdateDto clientUpdateDto);
}
