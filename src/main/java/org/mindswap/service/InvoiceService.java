package org.mindswap.service;

import org.mindswap.dto.InvoiceDto;
import org.mindswap.model.Invoice;
import org.mindswap.model.Rental;

import java.util.List;

public interface InvoiceService {


    InvoiceDto getInvoiceById(Long id);

    void deleteInvoiceById(Long id);

    List<InvoiceDto> getAllClientInvoices(Long clientId);

    public Invoice createInvoice(Rental rental, Long storeId);

    List<InvoiceDto> getAllInvoices();

    String getEmailFromService(Long invoiceId, Long userId,String role);


}
