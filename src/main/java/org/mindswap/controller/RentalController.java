package org.mindswap.controller;

import org.mindswap.dto.*;
import org.mindswap.service.MovieService;
import org.mindswap.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private RentalService rentalService;
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WORKER', 'MANAGER','ADMIN')")
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalCreateDto rentalCreateDto) {
        return new ResponseEntity<>(rentalService.createRental(rentalCreateDto), HttpStatus.CREATED);

    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable("{id}")Long id) {
        return new ResponseEntity<>(rentalService.getRentalById(id),HttpStatus.OK);
    }

    @GetMapping(path ="/all")
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        return new ResponseEntity<>(rentalService.getAllRentals(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<String> deleteRental(@PathVariable("{id}")Long id){
        rentalService.deleteRental(id);
        return new ResponseEntity<>("Rental has been Deleted",HttpStatus.OK);
    }

    @GetMapping(path ="/all/client/{id}")
    @PreAuthorize("hasAnyRole('WORKER', 'MANAGER','ADMIN')")
    public ResponseEntity<List<RentalDto>> getAllRentalsByClientId(@PathVariable("{id}")Long id){
        Long clientId = id;
        return new ResponseEntity<>(rentalService.getAllRentalsByClientIdJpa(clientId), HttpStatus.OK);
    }

}
