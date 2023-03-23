package org.mindswap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.mindswap.model.MovieGenre;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieCreateDto {
    @NotBlank(message = "Must  have a title")
    private String title;

    @NotNull(message = "must have a price")
    private double price;

    @NotNull(message = "Must have a genre")
    private MovieGenre movieGenre;
}
