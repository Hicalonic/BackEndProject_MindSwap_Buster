package org.mindswap.dto;

import lombok.*;
import org.mindswap.model.Movie;
import org.mindswap.model.User;

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

    private User client;

    private List<Movie> movieList;
}
