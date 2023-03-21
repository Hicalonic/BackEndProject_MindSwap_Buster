package org.mindswap.controller;

import org.mindswap.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminController adminController){
        this.adminService = (AdminService) adminController;
    }

    @GetMapping(path = "/create-shop")
    public ResponseEntity<String> CreateShop(){
        return ResponseEntity.ok("New shop was created!");
    }

    @PutMapping(path = "/update-shop")
    public ResponseEntity<String> ShopUpdate(){
        return null;  //("New manager was created!")
    }

    @GetMapping(path = "/create-manager")
    public ResponseEntity<String> CreateManager(){
        return ResponseEntity.ok("New manager was created!");
    }

    @PutMapping(path = "/update-admin")
    public ResponseEntity<String> UpdateAdmin(){
        return ResponseEntity.ok("New admin was created!");
    }
}