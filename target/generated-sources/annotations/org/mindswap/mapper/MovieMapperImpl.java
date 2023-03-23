package org.mindswap.mapper;

import javax.annotation.processing.Generated;
import org.mindswap.dto.MovieCreateDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.dto.MovieUpdateDto;
import org.mindswap.model.Movie;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T23:56:36+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie fromCreateDtoToEntity(MovieCreateDto movieCreateDto) {
        if ( movieCreateDto == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.title( movieCreateDto.getTitle() );
        movie.price( movieCreateDto.getPrice() );
        movie.movieGenre( movieCreateDto.getMovieGenre() );

        return movie.build();
    }

    @Override
    public MovieCreateDto fromEntityToCreateDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieCreateDto.MovieCreateDtoBuilder movieCreateDto = MovieCreateDto.builder();

        movieCreateDto.title( movie.getTitle() );
        movieCreateDto.price( movie.getPrice() );
        movieCreateDto.movieGenre( movie.getMovieGenre() );

        return movieCreateDto.build();
    }

    @Override
    public Movie fromDtoToEntity(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.title( movieDto.getTitle() );
        movie.price( movieDto.getPrice() );
        movie.movieGenre( movieDto.getMovieGenre() );

        return movie.build();
    }

    @Override
    public MovieDto fromEntityToDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto.MovieDtoBuilder movieDto = MovieDto.builder();

        movieDto.title( movie.getTitle() );
        movieDto.price( movie.getPrice() );
        movieDto.movieGenre( movie.getMovieGenre() );

        return movieDto.build();
    }

    @Override
    public Movie fromUpdateDtoToEntity(MovieUpdateDto movieUpdateDto) {
        if ( movieUpdateDto == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.title( movieUpdateDto.getTitle() );
        movie.price( movieUpdateDto.getPrice() );
        movie.movieGenre( movieUpdateDto.getMovieGenre() );

        return movie.build();
    }

    @Override
    public MovieUpdateDto fromEntityToUpdateDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieUpdateDto.MovieUpdateDtoBuilder movieUpdateDto = MovieUpdateDto.builder();

        movieUpdateDto.title( movie.getTitle() );
        movieUpdateDto.price( movie.getPrice() );
        movieUpdateDto.movieGenre( movie.getMovieGenre() );

        return movieUpdateDto.build();
    }
}
