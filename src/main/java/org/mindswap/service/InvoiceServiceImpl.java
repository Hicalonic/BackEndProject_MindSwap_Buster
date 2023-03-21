package org.mindswap.service;

import org.mindswap.dto.InvoiceDto;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService{

    private UserRepository userRepository;



    @Override
    public InvoiceDto getSpecificInvoice(Long invoiceID) {
        return null;
    }

    @Override
    public List<InvoiceDto> getSpecificClientInvoices(Long clientId) {
        return null;
    }
}
