package org.mindswap.service;

import jakarta.transaction.Transactional;
import org.mindswap.dto.CreateRentalJsonBody;
import org.mindswap.dto.RentalDto;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.exceptions.MovieNotFoundException;
import org.mindswap.exceptions.RentalNotFoundException;
import org.mindswap.mapper.RentalMapper;
import org.mindswap.model.Invoice;
import org.mindswap.model.Movie;
import org.mindswap.model.Rental;
import org.mindswap.model.User;
import org.mindswap.repository.MovieRepository;
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
    private MovieRepository movieRepository;
    private InvoiceService invoiceService;



    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper, UserRepository userRepository,
                             MovieRepository movieRepository, InvoiceService invoiceService) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.invoiceService = invoiceService;
    }


    @Override
    @Transactional
    public void createRental(CreateRentalJsonBody createRentalJsonBody) {
        Long clientId = createRentalJsonBody.getClientId();
        User user = userRepository.getReferenceById(clientId);

        List<Long> movieIdList = createRentalJsonBody.getMovieIdList();
        List<Movie> movieList = new ArrayList<>();

        for (Long movieId: movieIdList) {
            Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
            movieList.add(movie);
            movie.setAvailable(false);
            movieRepository.save(movie);
        }

        LocalDate startDate = createRentalJsonBody.getStartDate();
        LocalDate endDate = createRentalJsonBody.getEndDate();

        Rental rental = Rental.builder()
                .user(user)
                .movies(movieList)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Invoice invoice = invoiceService.createInvoice(rental, createRentalJsonBody.getStoreId());
        rental.setInvoice(invoice);

        rentalRepository.save(rental);
    }

    @Override
    public List<RentalDto> getClientCurrentRentals(Long clientID) {
        User user = userRepository.findById(clientID).orElseThrow(ClientNotFoundException::new);
        List<Rental> currentRentalList = user.getRentalList().stream()
                .filter(rental -> rental.getEndDate().isAfter(LocalDate.now())).toList();
        return currentRentalList.stream().map(r -> rentalMapper.fromEntityToDto(r)).toList();
    }

    @Override
    public RentalDto getRentalById(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        return rentalMapper.fromEntityToDto(rental);

    }

    @Override
    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream().map(r -> rentalMapper.fromEntityToDto(r)).toList();
    }


    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        rentalRepository.delete(rental);
    }

    @Override
    public List<RentalDto> getAllRentalsByClientId(Long clientId) {
        List<Rental> allUserRental = rentalRepository.findAll();
        List<RentalDto> allUserRentalDto = new ArrayList<>();
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
        return rentalRepository.findByInvoiceId(invoiceId);
    }

    @Override
    public void deliver(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
        List<Movie> movies = rental.getMovies();
        movies.forEach(movie -> movie.setAvailable(true));
        movies.forEach(movie->movieRepository.save(movie));
    }
}
