package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.model.Role;
import org.mindswap.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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


    -------
    if (role.equals(Role.CLIENT) && !authenticatedClientId.equals(clientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
     */


    @GetMapping(path = "")
    @Cacheable(value = "welcomeClient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CLIENT')")
    public String welcomeClient() throws InterruptedException {
        Thread.sleep(6000);
        return "Welcome to Blockbuster, dear client.";
    }



    @GetMapping(path = "/info")
    @PreAuthorize("hasAuthority('CLIENT')")
    //TODO TRY THIS CACHEABLE ANNOTATION
    //@Cacheable(value = "userInfo", key = "#authenticatedClientId")
    public ResponseEntity<UserDto> getMyInfo() {
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
        UserDto myInfoDto = clientService.getClientById(authenticatedClientId);
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @PutMapping(path = "/info")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> updateMyInfo(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
        clientService.updateClient(authenticatedClientId,userUpdateDto);
        return new ResponseEntity<>("Your information has been updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/info")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteMyInfo() {
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
        clientService.deleteClient(authenticatedClientId);
        return new ResponseEntity<>("Your account have been deleted.", HttpStatus.OK);
    }



    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<UserDto> getClientInfo(@PathVariable("id") Long clientId) {
        UserDto client = clientService.getClientById(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> updateClientInfo(@PathVariable("id") Long clientId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        clientService.updateClient(clientId, userUpdateDto);
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<List<UserDto>> getAllClients() {
        List<UserDto> clientList = clientService.getAllClients();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}
