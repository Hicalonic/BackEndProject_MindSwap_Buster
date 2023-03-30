package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.RentalCreateDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.model.Rental;
@Mapper(componentModel = "spring")
public interface RentalMapper {

    Rental fromDtoToEntity(RentalDto rentalDto);
    @InheritInverseConfiguration
    RentalDto fromEntityToDto (Rental rental);

    Rental fromCreateDtoToEntity(RentalCreateDto rentalCreateDto);
    @InheritInverseConfiguration
    RentalCreateDto fromEntityToCreateDto(Rental rental);


}
