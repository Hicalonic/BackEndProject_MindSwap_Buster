package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.*;
import org.mindswap.model.Role;
import org.mindswap.service.AdminService;
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
@RequestMapping("")
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PutMapping(path = "/{id}/make_admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> makeAdmin(@PathVariable("{id}") Long id) {
        Long userId = id;
        UserDto updatedUser = adminService.makeAdmin(userId);

        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}/make_manager")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> makeManager(@PathVariable("{id}") Long id) {
        Long userId = id;
        UserDto updatedUser = adminService.makeAdmin(userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}/make_worker")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> makeWorker(@PathVariable("{id}") Long id) {
        Long userId = id;
        UserDto updatedUser = adminService.makeAdmin(userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

}
