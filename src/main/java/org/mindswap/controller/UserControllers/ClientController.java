package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.ClientDto;
import org.mindswap.dto.ClientUpdateDto;
import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.dtosUser.UserClientDto;
import org.mindswap.dtosUser.UserClientUpdateDto;
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
    public ResponseEntity<UserClientDto> myInfo() {

        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
        UserClientDto myInfoDto = clientService.getClientById(authenticatedClientId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<UserClientDto> getClientInfo(@PathVariable("id") Long clientId) {
        UserClientDto client = clientService.getClientById(clientId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateClientInfo(@PathVariable("id") Long clientId, @Valid @RequestBody UserClientUpdateDto userClientUpdateDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.CLIENT) && !authenticatedClientId.equals(clientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        clientService.updateClient(clientId, userClientUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long clientId) {
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
    public ResponseEntity<List<UserClientDto>> getAllClients() {
        List<UserClientDto> clientList = clientService.getAllClients();

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/update-role")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<String> updateClientToWorkerRole(@PathVariable("id") Long clientId, @Valid @RequestBody RoleUpdateDto roleUpdateDto) {
        clientService.updateClientRole(clientId,roleUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Role updated to worker.", HttpStatus.OK);
    }

}
