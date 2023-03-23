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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
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
                .filter(rental -> rental.getEndDate().isAfter(LocalDate.now())).toList();
        return currentRentalList.stream().map(r-> rentalMapper.fromEntityToDto(r)).toList();
    }

    @Override
    public RentalDto getRentalById(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        return rentalMapper.fromEntityToDto(rental);

    }

    @Override
    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream().map(r->rentalMapper.fromEntityToDto(r)).toList();
    }

    @Override
    public RentalDto updateRental(Long rentalId, RentalUpdateDto rentalUpdateDto) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        if(rentalUpdateDto.getStartDate() != null) {
            rental.setStartDate(rentalUpdateDto.getStartDate());
        }
        if(rentalUpdateDto.getEndDate() != null) {
            rental.setEndDate(rentalUpdateDto.getEndDate());
        }
        if(rentalUpdateDto.getMovieList() != null) {
            rental.setMoviesRented(rentalUpdateDto.getMovieList());
        }
        rentalRepository.save(rental);
        return rentalMapper.fromEntityToDto(rental);

    }

    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        rentalRepository.delete(rental);
    }
}
