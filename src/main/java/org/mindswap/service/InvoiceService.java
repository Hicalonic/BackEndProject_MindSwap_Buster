package org.mindswap.service;

import org.mindswap.dto.InvoiceCreateDto;
import org.mindswap.dto.InvoiceDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.repository.InvoiceRepository;

import java.util.List;

public interface InvoiceService {

    RentalDto getSpecificInvoice(Long invoiceId, Long clientId);

    InvoiceDto getInvoiceById(Long id);

    void deleteInvoiceById(Long id);

    List<InvoiceDto> getAllClientInvoices(Long clientId);

    InvoiceDto createInvoice(InvoiceCreateDto invoiceCreateDto);

    List<InvoiceDto> getAllInvoices();


    //return html page with invoice details

    //return qrcode with link for the page

    //invoice is sent by email


}
