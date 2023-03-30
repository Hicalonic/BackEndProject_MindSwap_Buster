package org.mindswap.service;

import org.mindswap.dto.MovieCreateDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.dto.MovieUpdateDto;
import org.mindswap.model.Movie;

import java.util.List;

public interface MovieService {
    void createMovie(Long id);
    void createMovies(List<Long> imdbIds);

    public MovieDto getMovieById(Long movieId);

    List<MovieDto> getAvailableMovies();

    List<MovieDto> getAllMovies();
    public MovieDto updateMovie(Long movieId, MovieUpdateDto movieUpdateDto);
    public void deleteMovie(Long movieId);

    double generatePrice(double rating);



}
