package org.mindswap.service;

import org.mindswap.dto.MovieDto;

import java.util.List;

public interface MovieService {
    public MovieDto createMovie(MovieDto movieDto);
    public List<MovieDto> createMovies(List<MovieDto> moviesListDto);

    public MovieDto getMovieById(Long movieId);

    List<MovieDto> getAvailableMovies();
    public MovieDto updateMovie(Long movieId);
    public void deleteMovie(Long movieId);



}
