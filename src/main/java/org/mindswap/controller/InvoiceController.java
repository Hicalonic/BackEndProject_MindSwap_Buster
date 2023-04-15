package org.mindswap.controller;

import org.mindswap.dto.InvoiceDto;
import org.mindswap.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.mindswap.security.config.JwtAuthenticationFilter.getAuthenticatedUserId;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/{invoice_id}")
    @PreAuthorize("hasAnyAuthority('CLIENT','WORKER','MANAGER','ADMIN')")
    public ResponseEntity<InvoiceDto> getSpecificInvoice(@PathVariable("invoice_id") Long invoiceId) {


        return new ResponseEntity<>(invoiceService.getInvoiceById(invoiceId), HttpStatus.OK);
    }



    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices(){
        List<InvoiceDto> invoiceDtos = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoiceDtos, HttpStatus.OK);
    }

    @GetMapping(path = "all/client/{id}")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity <List<InvoiceDto>> getAllInvoicesPerClient(@PathVariable("{id}") Long clientId) {
        return new ResponseEntity<>(invoiceService.getAllClientInvoices(clientId), HttpStatus.OK);
    }


    @GetMapping(path = "{invoice_id}/email")
    @PreAuthorize("hasAnyAuthority('CLIENT','WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> getEmail(@PathVariable("invoice_id")Long invoiceId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedUserId = Long.valueOf(getAuthenticatedUserId());
        return new ResponseEntity<>(invoiceService.getEmailFromService(invoiceId,authenticatedUserId,role), HttpStatus.OK);

    }
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteInvoice(@PathVariable("id")Long invoiceId) {
        invoiceService.deleteInvoiceById(invoiceId);
        return new ResponseEntity<>("Invoice has been deleted",HttpStatus.OK);
    }
}
