package org.mindswap.controller;


import lombok.Getter;
import org.mindswap.dto.ClientDto;
import org.mindswap.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/worker/")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("")
    public ResponseEntity<String> WelcomeWorker() {
        return new ResponseEntity<>("Welcome back",HttpStatus.OK);
    }


    @GetMapping(path = "/info")
    public ResponseEntity<ClientDto> myInfo() {
        //TODO
        return null;
    }

    @GetMapping(path = "/info/{client_id}")
    public ResponseEntity<List<String>> getClientDetails(@PathVariable("client_id") Long client_id) {
        //TODO
        return null;
    }

    @GetMapping(path = "/info/{client_id}/rental/{rental_id}")
    public ResponseEntity<List<String>> getClientRentalDetail(@PathVariable("client_id") Long client_id, @PathVariable("rental_id") Long rentalId) {
        //TODO
        return null;
    }





}
