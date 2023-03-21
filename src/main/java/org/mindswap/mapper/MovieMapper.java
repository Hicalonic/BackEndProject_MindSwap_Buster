package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.MovieDto;
import org.mindswap.model.Movie;

@Mapper
public interface MovieMapper {


    MovieDto fromEntityToDto(Movie movie);
    @InheritInverseConfiguration
    Movie fromDtoToEntity(MovieDto movieDto);
}
