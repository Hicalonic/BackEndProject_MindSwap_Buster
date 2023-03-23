package org.mindswap.controller;


import org.mindswap.dto.ClientDto;
import org.mindswap.service.ClientService;
import org.mindswap.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "")
    public ResponseEntity<String> welcomeClient() {
        return new ResponseEntity<>("Welcome to Blockbuster", HttpStatus.OK);
    }



    @GetMapping(path = "/info")
    public ResponseEntity<ClientDto> myInfo() {
        //TODO
        return null;
    }

    @GetMapping(path = "/available-movies")
    public ResponseEntity<List<String>> getAvailableMovies() {
        //TODO
        return null;
    }

    @GetMapping(path = "/rental")
    public ResponseEntity<List<String>> getAllMyRentals() {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //Check if the {client_id} is the same as the token.client_id
        //TODO Body
        return null;
    }

    @GetMapping(path = "/rental/{rental_id}")
    public ResponseEntity<Object> getMyRentalDetails(@PathVariable("rental_id") Long rental_id) {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //Check if the client has this rental on his list
        //TODO Body
        return null;
    }

    @GetMapping(path = "/invoice")
    public ResponseEntity<List<String>> getAllMyInvoices() {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //Check if the client has this invoice on his list
        //TODO Body
        return null;
    }

//    @GetMapping(path = "/invoice/{invoice_id}/details")
//    public ResponseEntity<Object> getMyInvoiceDetails(@PathVariable("invoice_id") Long invoiceId) {
//        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
//        //Check if the client has this invoice on his list
//        //TODO Body
//        return null;
//    }

    @GetMapping(path = "/invoice/{invoice_id}/qrcode")
    public ResponseEntity<Object> getMyInvoiceToQrcode(@PathVariable("invoice_id") Long invoiceId) {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //Check if the client has this invoice on his list
        //Invoice service will ask QRCodeService to generate QRCode
        //TODO Body
        return null;
    }

    @GetMapping(path = "invoice/{invoice_id}/email")
    public ResponseEntity<String> getMyInvoiceToEmail(@PathVariable("invoice_id") Long invoiceId) {
        //ClientDto userDto = clientService.getClientByEmail(JwtAuthenticationFilter.getUserEmail());
        //Check if the client has this invoice on his list
        //Invoice service will ask EmailService to send Email
        //TODO Body
        return null;
    }
}
