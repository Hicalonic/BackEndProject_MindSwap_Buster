package org.mindswap.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.mindswap.model.Movie;
import org.mindswap.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalDto {

    @NotBlank(message = "Needs a costumer id ")
    private Long clientId;
    @NotBlank(message = "Needs movies")
    private List<Long> movieIdList;

    @NotBlank(message = "Needs a start date")
    private LocalDate startDate;
    @NotBlank(message = "Needs a delivery date")
    private LocalDate endDate;


}
