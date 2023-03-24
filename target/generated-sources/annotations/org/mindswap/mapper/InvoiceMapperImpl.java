package org.mindswap.mapper;

import javax.annotation.processing.Generated;
import org.mindswap.dto.InvoiceCreateDto;
import org.mindswap.dto.InvoiceDto;
import org.mindswap.dto.InvoiceUpdateDto;
import org.mindswap.model.Invoice;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-24T10:36:59+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice fromDtoToEntity(InvoiceDto invoiceDto) {
        if ( invoiceDto == null ) {
            return null;
        }

        Invoice.InvoiceBuilder invoice = Invoice.builder();

        if ( invoiceDto.getPrice() != null ) {
            invoice.price( invoiceDto.getPrice() );
        }

        return invoice.build();
    }

    @Override
    public InvoiceDto fromEntityToDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDto.InvoiceDtoBuilder invoiceDto = InvoiceDto.builder();

        invoiceDto.price( (long) invoice.getPrice() );

        return invoiceDto.build();
    }

    @Override
    public Invoice fromCreateDtoToEntity(InvoiceCreateDto invoiceCreateDto) {
        if ( invoiceCreateDto == null ) {
            return null;
        }

        Invoice.InvoiceBuilder invoice = Invoice.builder();

        invoice.price( invoiceCreateDto.getPrice() );

        return invoice.build();
    }

    @Override
    public InvoiceCreateDto fromCreateDtoToEntity(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceCreateDto.InvoiceCreateDtoBuilder invoiceCreateDto = InvoiceCreateDto.builder();

        invoiceCreateDto.price( invoice.getPrice() );

        return invoiceCreateDto.build();
    }

    @Override
    public Invoice fromUpdateDtoToEntity(InvoiceUpdateDto invoiceUpdateDto) {
        if ( invoiceUpdateDto == null ) {
            return null;
        }

        Invoice.InvoiceBuilder invoice = Invoice.builder();

        if ( invoiceUpdateDto.getPrice() != null ) {
            invoice.price( invoiceUpdateDto.getPrice() );
        }

        return invoice.build();
    }

    @Override
    public InvoiceUpdateDto fromEntityToUpdateDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceUpdateDto.InvoiceUpdateDtoBuilder invoiceUpdateDto = InvoiceUpdateDto.builder();

        invoiceUpdateDto.price( (long) invoice.getPrice() );

        return invoiceUpdateDto.build();
    }
}
