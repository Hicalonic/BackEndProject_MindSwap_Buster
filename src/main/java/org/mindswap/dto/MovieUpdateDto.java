package org.mindswap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDto {
    @NotBlank(message = "Must  have a title")
    private String title;

    @NotNull(message = "must have a price")
    private double price;

    @NotNull(message = "must have a rating")
    private double imdbRating;

    @NotNull(message = "Must have a genre")
    private String movieGenre;
}
