package org.mindswap.controller;


import org.mindswap.auth.AuthenticationResponse;
import org.mindswap.auth.AuthenticationService;
import org.mindswap.auth.RegisterRequest;
import org.mindswap.dto.ClientDto;
import org.mindswap.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/manager")
public class ManagerController {

    private final AuthenticationService authenticationService;
    private ManagerService managerService;

    @Autowired
    public ManagerController(AuthenticationService authenticationService, ManagerService managerService) {
        this.authenticationService = authenticationService;
        this.managerService = managerService;
    }

    @GetMapping("")
    public ResponseEntity<String> WelcomeWorker() {
        return new ResponseEntity<>("Welcome back, Manager", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    public ResponseEntity<ClientDto> myInfo() {
        //TODO
        return null;
    }

    @PostMapping(path = "/insert-movie")
    public ResponseEntity<String> insertMovie() {
        //TODO
        return null;
    }

    @PatchMapping(path = "/update-movie")
    public ResponseEntity<String> updateMovie() {
        //TODO
        return null;
    }

    @DeleteMapping(path = "delete/{movie_id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movie_id") Long movieId) {
        //TODO
        return null;
    }

    @PostMapping("/register-worker")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @DeleteMapping(path = "/remove-worker/{worker_id}")
    public ResponseEntity<String> removeWorker(@PathVariable("worker_id") Long workerId) {
        //TODO
        return null;
    }







}
