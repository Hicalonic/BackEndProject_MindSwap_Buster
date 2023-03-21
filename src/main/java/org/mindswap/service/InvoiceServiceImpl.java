package org.mindswap.service;

import org.mindswap.dto.InvoiceDto;
import org.mindswap.mapper.InvoiceMapper;
import org.mindswap.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceDto getSpecificInvoice(Long invoiceID) {
        return null;
    }

    @Override
    public List<InvoiceDto> getSpecificClientInvoices(Long clientId) {
        return null;
    }
}
