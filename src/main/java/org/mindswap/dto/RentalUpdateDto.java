package org.mindswap.dto;

import lombok.*;
import org.mindswap.model.Client;
import org.mindswap.model.Movie;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentalUpdateDto {
    private LocalDate startDate;

    private LocalDate endDate;

    private Client client;

    private List<Movie> movieList;
}
