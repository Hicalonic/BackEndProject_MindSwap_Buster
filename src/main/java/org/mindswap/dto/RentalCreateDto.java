package org.mindswap.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.mindswap.model.Movie;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreateDto {
    @NotBlank(message = "Needs a start date")
    private Date startDate;

    @NotBlank(message = "Needs a delivery date")
    private Date endDate;

    @NotBlank(message = "Needs a costumer")
    private Client client;

    @NotBlank(message = "Needs movies")
    private List<Movie> movieList;
}
