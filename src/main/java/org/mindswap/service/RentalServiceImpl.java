package org.mindswap.service;

import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.mapper.RentalMapper;
import org.mindswap.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;

    private RentalMapper rentalMapper;
    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public RentalDto createRental(RentalDto rentalDto) {
        return null;
    }

    @Override
    public List<RentalDto> getClientCurrentRentals(Long clientID) {
        return null;
    }

    @Override
    public RentalDto getRentalById(Long rentalId) {
        return null;
    }

    @Override
    public List<RentalDto> getAllRentals() {
        return null;
    }

    @Override
    public RentalDto updateRental(Long rentalId, RentalUpdateDto rentalUpdateDto) {
        return null;
    }

    @Override
    public void deleteRental(Long rentalId) {

    }
}
