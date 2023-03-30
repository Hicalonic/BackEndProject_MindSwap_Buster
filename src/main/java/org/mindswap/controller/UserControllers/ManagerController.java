package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserDtoJsonBody;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.model.Role;
import org.mindswap.service.ManagerService;
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
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping(path = "")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> welcomeManager() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear manager.", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<UserDtoJsonBody> myInfo() {
        Long authenticatedManagerId = Long.valueOf(getAuthenticatedUserId());
        UserDtoJsonBody myInfoDto = managerService.getInfoById(authenticatedManagerId);
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @PutMapping(path = "/info")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> updateMyInfo(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        Long authenticatedWManagerId = Long.valueOf(getAuthenticatedUserId());
        managerService.updateManager(authenticatedWManagerId,userUpdateDto);
        return new ResponseEntity<>("Your information has been updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/info")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> deleteMyInfo() {
        Long authenticatedManagerId = Long.valueOf(getAuthenticatedUserId());
        managerService.deleteManager(authenticatedManagerId);
        return new ResponseEntity<>("Your account has been deleted.", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDtoJsonBody> getManagerInfo(@PathVariable("id") Long managerId) {
        UserDtoJsonBody manager = managerService.getInfoById(managerId);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateManagerInfo(@PathVariable("id") Long managerId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        managerService.updateManager(managerId, userUpdateDto);
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteManager(@PathVariable("id") Long managerId) {
        managerService.deleteManager(managerId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDtoJsonBody>> getAllManagers() {
        List<UserDtoJsonBody> managersList = managerService.getAllManagers();
        return new ResponseEntity<>(managersList, HttpStatus.OK);
    }
}