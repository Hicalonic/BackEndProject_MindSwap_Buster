package org.mindswap.controller.UserControllers;


import org.mindswap.dto.ClientDto;
import org.mindswap.dto.InvoiceDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private WorkerService workerService;
    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("")
    public ResponseEntity<String> WelcomeWorker() {
        return new ResponseEntity<>("Welcome back, Worker",HttpStatus.OK);
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

    @GetMapping(path = "/info/{client_id}/rental/")
    public ResponseEntity<List<String>> getAllRentalsOfClient(@PathVariable("client_id") Long client_id) {
        //TODO
        return null;
    }

    @GetMapping(path = "/info/{client_id}/rental/{rental_id}")
    public ResponseEntity<List<String>> getSpecificRentalOfClient(@PathVariable("client_id") Long client_id, @PathVariable("rental_id") Long rentalId) {
        //TODO
        return null;
    }

    @PatchMapping(path = "/info/{client_id}/rental/{rental_id}/deliver")
    public ResponseEntity<List<String>> updateRentalDeliveredStatus(@PathVariable("client_id") Long client_id, @PathVariable("rental_id") Long rentalId) {
        //Updates the isDelivered boolean to updated
        return null;
    }

    @GetMapping(path = "/info/{client_id}/invoice/")
    public ResponseEntity<List<String>> getAllInvoicesOfClient(@PathVariable("client_id") Long client_id) {
        //TODO
        return null;
    }

    @GetMapping(path = "/info/{client_id}/invoice/{invoice_id}/details")
    public ResponseEntity<List<String>> getSpecificInvoiceOfClient(@PathVariable("client_id") Long client_id, @PathVariable("invoice_id") Long rentalId) {
        //TODO
        return null;
    }

    @GetMapping(path = "/info/{client_id}/invoice/{invoice_id}/qrcode")
    public ResponseEntity<List<String>> sendQrcodeOfInvoiceToClient(@PathVariable("client_id") Long client_id, @PathVariable("invoice_id") Long rentalId) {
        //TODO
        return null;
    }

    @GetMapping(path = "/info/{client_id}/invoice/{invoice_id}/email")
    public ResponseEntity<List<String>> sendEmailOfInvoiceToClient(@PathVariable("client_id") Long client_id, @PathVariable("invoice_id") Long rentalId) {
        //TODO
        return null;
    }

    @GetMapping(path = "/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        //TODO
        return null;
    }

    @GetMapping(path = "/movies/available")
    public ResponseEntity<List<MovieDto>> getAllAvailableMovies() {
        //TODO
        return null;
    }

    @GetMapping(path = "/movies/available/store")
    public ResponseEntity<List<MovieDto>> getAllAvailableMoviesByWorkersStore() {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //userDto.getStore...
        //getMoviesByStore
        //TODO Body
        return null;
    }
    @GetMapping(path= "/movies/rented")
    public ResponseEntity<List<MovieDto>> getAllRentedMovies() {
        //TODO
        return null;
    }

    @GetMapping(path = "/movies/rented/store")
    public ResponseEntity<List<MovieDto>> getAllRentedMoviesByWorkersStore() {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //userDto.getStore...
        //getMoviesByStore
        //TODO Body
        return null;
    }

    @GetMapping(path = "/client-list")
    public ResponseEntity<List<ClientDto>> getClientsList() {
        //TODO Body
        return null;
    }

    @GetMapping(path = "/rental-list")
    public ResponseEntity<List<RentalDto>> getRentalsList() {
        //TODO Body
        return null;
    }

    @PostMapping(path = "/create-order")
    public ResponseEntity<RentalDto> createOrder() {
        //TODO Body
        return null;
    }

    @PostMapping(path = "/create-invoice")
    public ResponseEntity<InvoiceDto> createInvoice() {
        //TODO Body
        return null;
    }
}
