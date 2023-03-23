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
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> welcomeAdmin() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear god.", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDto> myInfo() {
        Long authenticatedAdminId = Long.valueOf(getAuthenticatedUserId());
        AdminDto myInfoDto = adminService.getAdminById(authenticatedAdminId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAdminInfo(@PathVariable("id") Long adminId) {
        AdminDto admin = adminService.getAdminById(adminId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateAdminInfo(@PathVariable("id") Long adminId, @Valid @RequestBody AdminUpdateDto adminUpdateDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedAdminId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.ADMIN) && !authenticatedAdminId.equals(adminId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        adminService.updateAdmin(adminId, adminUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long adminId) {

        adminService.deleteAdmin(adminId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> adminsList = adminService.getAllAdmins();

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(adminsList, HttpStatus.OK);
    }
}