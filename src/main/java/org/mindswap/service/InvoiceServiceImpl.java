package org.mindswap.service;

import jakarta.transaction.Transactional;
import org.mindswap.dto.InvoiceCreateDto;
import org.mindswap.dto.InvoiceDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.exceptions.InvoiceNotFoundException;
import org.mindswap.mapper.InvoiceMapper;
import org.mindswap.model.Invoice;
import org.mindswap.model.Movie;
import org.mindswap.model.Rental;
import org.mindswap.model.User;
import org.mindswap.repository.InvoiceRepository;
import org.mindswap.repository.StoreRepository;
import org.mindswap.repository.UserRepository;
import org.mindswap.utils.PDF.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;

    private UserRepository userRepository;

    private StoreRepository storeRepository;

    private PdfGenerator pdfGenerator;


    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,
                              UserRepository userRepository, StoreRepository storeRepository, PdfGenerator pdfGenerator) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.userRepository = userRepository;
        this.storeRepository=storeRepository;
        this.pdfGenerator = pdfGenerator;

    }




//    @Override
//    public RentalDto getSpecificInvoice(Long invoiceId, Long clientId)  {
//        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);
//        Rental rental =  rentalService.getRentalByInvoiceId(invoiceId);
//        return rentalService.getRentalById(rental.getId());
//
//    }

    @Override
    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(InvoiceNotFoundException::new);
        return invoiceMapper.fromEntityToDto(invoice);
    }

    @Override
    public void deleteInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(InvoiceNotFoundException::new);
        invoiceRepository.delete(invoice);
    }


    @Override
    public List<InvoiceDto> getAllClientInvoices(Long clientId) {
        User client = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        List<Invoice> invoiceList = new ArrayList<>();
        client.getRentalList().forEach(r -> invoiceList.add(r.getInvoice()));
        return invoiceList.stream().map(i-> invoiceMapper.fromEntityToDto(i)).toList();
    }


    @Override
    @Transactional
    public Invoice createInvoice(Rental rental, Long storeId) {
        Invoice invoice = new Invoice();
        double rentalPrice = 0;

        for (Movie movie: rental.getMovies()) {
            rentalPrice += movie.getPrice();
        }


        invoice.setStore(storeRepository.getReferenceById(storeId));
        invoice.setRental(rental);
        invoice.setPrice(rentalPrice);
        invoiceRepository.save(invoice);
        pdfGenerator.createPDF("InvoicePDF".concat(invoice.getId().toString()).concat(".pdf"), invoice);
        return invoice;
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(i -> invoiceMapper.fromEntityToDto(i)).toList();
    }


}