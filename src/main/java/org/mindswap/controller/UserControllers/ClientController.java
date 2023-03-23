package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.ClientDto;
import org.mindswap.dto.ClientUpdateDto;
import org.mindswap.model.Role;
import org.mindswap.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.mindswap.security.config.JwtAuthenticationFilter.getAuthenticatedUserId;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
    --------------------------INFORMATION ABOUT WHO'S ASKING------------------
    ------EMAIL------
    String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    -------ID-------
    Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
    -----ROLE-----
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String role = auth.getAuthorities().iterator().next().getAuthority();
     */


    @GetMapping(path = "")
    public ResponseEntity<String> welcomeClient() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear client.", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClientDto> myInfo() {

        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
        ClientDto myInfoDto = clientService.getClientById(authenticatedClientId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<Object> getClientInfo(@PathVariable("id") Long clientId) {
        ClientDto client = clientService.getClientById(clientId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateClientInfo(@PathVariable("id") Long clientId, @Valid @RequestBody ClientUpdateDto clientUpdateDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.CLIENT) && !authenticatedClientId.equals(clientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        clientService.updateClient(clientId, clientUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Long clientId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.CLIENT) && !authenticatedClientId.equals(clientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        clientService.deleteClient(clientId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clientList = clientService.getAllClients();

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}