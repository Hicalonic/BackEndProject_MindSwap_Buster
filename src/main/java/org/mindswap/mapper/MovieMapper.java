package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.MovieCreateDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.dto.MovieUpdateDto;
import org.mindswap.model.Movie;

@Mapper
public interface MovieMapper {

    Movie fromCreateDtoToEntity(MovieCreateDto movieCreateDto);
    @InheritInverseConfiguration
    MovieCreateDto fromEntityToCreateDto(Movie movie);

    Movie fromDtoToEntity(MovieDto movieDto);
    @InheritInverseConfiguration
    MovieDto fromEntityToDto(Movie movie);

    Movie fromUpdateDtoToEntity(MovieUpdateDto movieUpdateDto);
    @InheritInverseConfiguration
    MovieUpdateDto fromEntityToUpdateDto(Movie movie);


}
