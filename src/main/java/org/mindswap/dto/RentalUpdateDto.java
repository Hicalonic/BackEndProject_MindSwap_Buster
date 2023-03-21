package org.mindswap.dto;

import org.mindswap.model.Client;
import org.mindswap.model.Movie;

import java.time.LocalDate;
import java.util.List;

public class RentalUpdateDto {
    private LocalDate startDate;

    private LocalDate deliveryDate;

    private Client client;

    private List<Movie> movieList;
}
