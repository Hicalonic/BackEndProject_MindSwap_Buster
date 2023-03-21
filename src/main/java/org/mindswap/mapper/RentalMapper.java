package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mindswap.dto.RentalCreateDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.model.Rental;

public interface RentalMapper {

    Rental fromDtoToEntity(RentalDto rentalDto);
    @InheritInverseConfiguration
    RentalDto fromEntityToDto (Rental rental);

    Rental fromCreateDtoToEntity(RentalCreateDto rentalCreateDto);
    @InheritInverseConfiguration
    RentalCreateDto fromEntityToCreateDto(Rental rental);

    Rental fromUpdateDtoToEntity (RentalUpdateDto rentalUpdateDto);
    @InheritInverseConfiguration
    RentalUpdateDto fromEntityToUpdateDto(Rental rental);




}
