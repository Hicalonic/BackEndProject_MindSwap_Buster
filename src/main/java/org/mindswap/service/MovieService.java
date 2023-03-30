package org.mindswap.service;

import org.mindswap.dto.MovieDto;

import java.util.List;

public interface MovieService {
    void createMovie(Long id);
    void createMovies(List<Long> imdbIds);

    public MovieDto getMovieById(Long movieId);

    List<MovieDto> getAvailableMovies();

    List<MovieDto> getAllMovies();
    public void deleteMovie(Long movieId);

    double generatePrice(double rating);



}
