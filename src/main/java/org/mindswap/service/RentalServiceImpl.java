package org.mindswap.service;

import org.mindswap.dto.RentalCreateDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.exceptions.RentalNotFoundException;
import org.mindswap.mapper.RentalMapper;
import org.mindswap.model.Rental;
import org.mindswap.model.User;
import org.mindswap.repository.RentalRepository;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper;
    private UserRepository userRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.userRepository = userRepository;
    }




    @Override
    public RentalDto createRental(RentalCreateDto rentalCreateDto) {
        Rental rental = rentalMapper.fromCreateDtoToEntity(rentalCreateDto);
        rentalRepository.save(rental);
        return rentalMapper.fromEntityToDto(rental);
    }

    @Override
    public List<RentalDto> getClientCurrentRentals(Long clientID) {
       User user = userRepository.findById(clientID).orElseThrow(ClientNotFoundException::new);
        List<Rental> currentRentalList = user.getRentalList().stream()
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
        /*if(rentalUpdateDto.getMovieList() != null) {
            rental.setMoviesRented(rentalUpdateDto.getMovieList());
        }*/
        rentalRepository.save(rental);
        return rentalMapper.fromEntityToDto(rental);

    }

    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        rentalRepository.delete(rental);
    }

    @Override
    public List<RentalDto> getAllRentalsByClientId(Long clientId) {
        List<Rental> allUserRental = rentalRepository.findAll();
        List<RentalDto> allUserRentalDto =new ArrayList<>();
        for (Rental rental :
                allUserRental) {
            allUserRentalDto.add(rentalMapper.fromEntityToDto(rental));
            }

        allUserRentalDto.removeIf(rentalDto -> !Objects.equals(rentalDto.getUser().getId(), clientId));
        return allUserRentalDto;
    }

    @Override
    public List<RentalDto> getAllRentalsByClientIdJpa(Long clientId) {
        List<Rental> rentals = rentalRepository.findByUser(userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new));
        List<RentalDto> rentalDtos = new ArrayList<>();
        for (Rental rental :
                rentals) {
            rentalDtos.add(rentalMapper.fromEntityToDto(rental));
        }
        return rentalDtos;
    }

    @Override
    public Rental getRentalByInvoiceId(Long invoiceId) {
      return  rentalRepository.findByInvoiceId(invoiceId);
    }
}
