package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.MovieCreateDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.model.Movie;
import org.mindswap.utils.IMDBAPI.ImdbMovieModel;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie fromCreateDtoToEntity(MovieCreateDto movieCreateDto);
    @InheritInverseConfiguration
    MovieCreateDto fromEntityToCreateDto(Movie movie);

    Movie fromDtoToEntity(MovieDto movieDto);
    @InheritInverseConfiguration
    MovieDto fromEntityToDto(Movie movie);

    Movie fromImdbMovieModelToMovie(ImdbMovieModel imdbMovieModel);


}
