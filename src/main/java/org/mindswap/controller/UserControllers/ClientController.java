package org.mindswap.controller.UserControllers;

import jakarta.validation.Valid;
import org.mindswap.dto.UserDtoJsonBody;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.model.User;
import org.mindswap.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping(path = "")
    @Cacheable(value = "welcomeClient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CLIENT')")
    public String welcomeClient(@AuthenticationPrincipal User activeUser) throws InterruptedException {
        //Test redis cache.
        Thread.sleep(6000);
        return "Welcome to MindSwapBuster, dear user.";
    }


    @GetMapping(path = "/info")
    @PreAuthorize("hasAnyAuthority('CLIENT','ADMIN')")
    //@Cacheable(value = "userInfo", key = "#authenticatedClientId")
    public ResponseEntity<UserDtoJsonBody> getMyInfo(@AuthenticationPrincipal User activeUser) {
        UserDtoJsonBody myInfoDto = clientService.getClientById(activeUser.getId());
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
    public ResponseEntity<UserDtoJsonBody> getClientInfo(@PathVariable("id") Long clientId) {
        UserDtoJsonBody client = clientService.getClientById(clientId);
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
    public ResponseEntity<List<UserDtoJsonBody>> getAllClients() {
        List<UserDtoJsonBody> clientList = clientService.getAllClients();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}
