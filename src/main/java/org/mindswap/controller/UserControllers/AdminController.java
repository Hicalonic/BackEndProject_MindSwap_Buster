package org.mindswap.controller.UserControllers;


import org.mindswap.dto.*;
import org.mindswap.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;

    }

    @GetMapping(path = "/{id}/make_admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> makeAdmin(@PathVariable("{id}") Long id) {
        UserDto updatedUser = adminService.makeAdmin(id);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}/make_manager")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> makeManager(@PathVariable("{id}") Long id) {
        UserDto updatedUser = adminService.makeManager(id);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}/make_worker")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> makeWorker(@PathVariable("{id}") Long id) {
        UserDto updatedUser = adminService.makeWorker(id);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

}