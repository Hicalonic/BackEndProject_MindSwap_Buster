package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.model.Rental;

public interface RentalMapper {

    RentalDto fromEntityToDto (Rental rental);
    @InheritInverseConfiguration
    Rental fromDtoToEntity(RentalDto rentalDto);

    RentalUpdateDto fromEntityToUpdateDto(Rental rental);
    @InheritInverseConfiguration
    Rental fromUpdateDtoToEntity (RentalUpdateDto rentalUpdateDto);


}
