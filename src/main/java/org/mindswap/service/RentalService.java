package org.mindswap.service;

import org.mindswap.dto.RentalCreateDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.model.Rental;

import java.util.List;

public interface RentalService {

    RentalDto createRental (RentalCreateDto rentalCreateDto);
    List<RentalDto> getClientCurrentRentals(Long clientID);
    RentalDto getRentalById(Long rentalId);
    List<RentalDto> getAllRentals();
    RentalDto updateRental(Long rentalId, RentalUpdateDto rentalUpdateDto);
    void deleteRental(Long rentalId);
    List<RentalDto> getAllRentalsByClientId(Long clientId);
    List<RentalDto> getAllRentalsByClientIdJpa(Long clientId);

    Rental getRentalByInvoiceId(Long invoiceId);
}
