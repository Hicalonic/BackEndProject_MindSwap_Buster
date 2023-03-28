package org.mindswap.controller;

import org.mindswap.dto.InvoiceCreateDto;
import org.mindswap.dto.InvoiceDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.model.Role;
import org.mindswap.service.InvoiceService;
import org.mindswap.utils.QRCodeGenerator;
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
    private QRCodeGenerator qrCodeGenerator;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, QRCodeGenerator qrCodeGenerator) {
        this.invoiceService = invoiceService;
        this.qrCodeGenerator = qrCodeGenerator;
    }

    @GetMapping(path = "/{invoice_id}")
    public ResponseEntity<InvoiceDto> getSpecificInvoice(@PathVariable("invoice_id") Long invoiceId) {
        Long authenticatedClientId = Long.valueOf(getAuthenticatedUserId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        RentalDto rentalDto = invoiceService.getSpecificInvoice(invoiceId, authenticatedClientId);
        if(rentalDto.getUser().getId() != authenticatedClientId && role.equals(Role.CLIENT)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(invoiceService.getInvoiceById(invoiceId), HttpStatus.OK);
    }

//    @GetMapping(path = "/{id}")
//    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(invoiceService.getInvoiceById(id),HttpStatus.OK);
//    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices(){
        List<InvoiceDto> invoiceDtos = invoiceService.getAllInvoices();
        if(invoiceDtos.size() != 0){
            for (InvoiceDto invoiceDto :
                 invoiceDtos) {
                qrCodeGenerator.generateQRCode(invoiceDto);
            }
        }
        return new ResponseEntity<>(invoiceDtos, HttpStatus.OK);
    }

    @GetMapping(path = "all/client/{id}")
    public ResponseEntity <List<InvoiceDto>> getAllInvoicesPerClient(@PathVariable("{id}") Long clientId) {
        return new ResponseEntity<>(invoiceService.getAllClientInvoices(clientId), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('WORKER,'MANAGER','ADMIN')")
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceCreateDto invoiceCreateDto) {
        return new ResponseEntity<>(invoiceService.createInvoice(invoiceCreateDto), HttpStatus.OK);
    }

    @GetMapping(path = "{id}/qrcode")
    public ResponseEntity<Object> getQrCode(@PathVariable("id") Long invoiceId) {
        return null;
    }

    @GetMapping(path = "{id}/pdf")
    public ResponseEntity<Object> getPdf(@PathVariable("id")Long invoiceId) {
        return null;
    }

    @GetMapping(path = "{id}/email")
    public ResponseEntity<Object> getEmail(@PathVariable("id")Long invoiceId) {
        return null;
    }
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteInvoice(@PathVariable("id")Long invoiceId) {

        invoiceService.deleteInvoiceById(invoiceId);
        return new ResponseEntity<>("Invoice has been deleted",HttpStatus.OK);
    }
}
