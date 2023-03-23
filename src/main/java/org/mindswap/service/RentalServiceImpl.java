package org.mindswap.service;

import org.mindswap.dto.RentalCreateDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.exceptions.RentalNotFoundException;
import org.mindswap.mapper.RentalMapper;
import org.mindswap.model.Client;
import org.mindswap.model.Rental;
import org.mindswap.repository.ClientRepository;
import org.mindswap.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper;
    private ClientRepository clientRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper, ClientRepository clientRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.clientRepository = clientRepository;
    }




    @Override
    public RentalDto createRental(RentalCreateDto rentalCreateDto) {
        Rental rental = rentalMapper.fromCreateDtoToEntity(rentalCreateDto);
        rentalRepository.save(rental);
        return rentalMapper.fromEntityToDto(rental);
    }

    @Override
    public List<RentalDto> getClientCurrentRentals(Long clientID) {
       Client client = clientRepository.findById(clientID).orElseThrow(ClientNotFoundException::new);
        List<Rental> currentRentalList = client.getRentalList().stream()
                .filter(rental -> rental.getEnDDate().isAfter(LocalDate.now())).toList();
        return currentRentalList.stream().map(r-> rentalMapper.fromEntityToDto(r)).toList();
    }

    @Override
    public RentalDto getRentalById(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        return rentalMapper.fromEntityToDto(rental);

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
