package org.mindswap.service;

import org.mindswap.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {

    public InvoiceDto getSpecificInvoice (Long invoiceID);
    public List<InvoiceDto> getSpecificClientInvoices (Long clientId);

    //return html page with invoice details

    //return qrcode with link for the page

    //invoice is sent by email


}
