package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.InvoiceCreateDto;
import org.mindswap.dto.InvoiceDto;
import org.mindswap.dto.InvoiceUpdateDto;
import org.mindswap.model.Invoice;

@Mapper
public interface InvoiceMapper {

    Invoice fromDtoToEntity(InvoiceDto invoiceDto);
    @InheritInverseConfiguration
    InvoiceDto fromEntityToDto(Invoice invoice);

    Invoice fromCreateDtoToEntity(InvoiceCreateDto invoiceCreateDto);
    @InheritInverseConfiguration
    InvoiceCreateDto fromCreateDtoToEntity(Invoice invoice);
    Invoice fromUpdateDtoToEntity(InvoiceUpdateDto invoiceUpdateDto );
    @InheritInverseConfiguration
    InvoiceUpdateDto fromEntityToUpdateDto(Invoice invoice);


}
