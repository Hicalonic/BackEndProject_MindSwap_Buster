package org.mindswap.service;

import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;

import java.util.List;

public interface RentalService {
    RentalDto getRentalById(Long rentalId);
    List<RentalDto> getAllRentals();
    RentalDto updateRental(Long rentalId, RentalUpdateDto rentalUpdateDto);
}
