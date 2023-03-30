package org.mindswap.service;

import org.mindswap.dto.CreateRentalJsonBody;
import org.mindswap.dto.RentalDto;
import org.mindswap.model.Rental;

import java.util.List;

public interface RentalService {

    void createRental (CreateRentalJsonBody createRentalJsonBody);
    List<RentalDto> getClientCurrentRentals(Long clientID);
    RentalDto getRentalById(Long rentalId);
    List<RentalDto> getAllRentals();
    void deleteRental(Long rentalId);
    List<RentalDto> getAllRentalsByClientId(Long clientId);
    List<RentalDto> getAllRentalsByClientIdJpa(Long clientId);

    Rental getRentalByInvoiceId(Long invoiceId);

    public void deliver(Long id);
}
