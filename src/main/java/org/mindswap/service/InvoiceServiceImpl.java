package org.mindswap.service;

import org.mindswap.dto.InvoiceDto;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.exceptions.InvoiceNotFoundException;
import org.mindswap.mapper.InvoiceMapper;
import org.mindswap.model.Invoice;
import org.mindswap.model.User;
import org.mindswap.repository.InvoiceRepository;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;

    private UserRepository userRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.userRepository = userRepository;
    }




    @Override
    public InvoiceDto getSpecificInvoice(Long invoiceID)  {
      Invoice invoice = invoiceRepository.findById(invoiceID).orElseThrow(InvoiceNotFoundException::new);
        return invoiceMapper.fromEntityToDto(invoice);
    }

    @Override
    public List<InvoiceDto> getSpecificClientInvoices(Long clientId) {
            User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
            List<Invoice> invoiceList = new ArrayList<>();
            user.getRentalList().forEach(r -> invoiceList.add(r.getInvoice()));
            return invoiceList.stream().map(i-> invoiceMapper.fromEntityToDto(i)).toList();
    }
}
