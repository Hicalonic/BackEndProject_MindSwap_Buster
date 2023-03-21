package org.mindswap.service;

import org.mindswap.dto.MovieDto;
import org.mindswap.mapper.MovieMapper;
import org.mindswap.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    private MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }



    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        return null;
    }

    @Override
    public List<MovieDto> createMovies(List<MovieDto> moviesListDto) {
        return null;
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
        return null;
    }

    @Override
    public List<MovieDto> getAvailableMovies() {
        return null;
    }

    @Override
    public MovieDto updateMovie(Long movieId) {
        return null;
    }

    @Override
    public void deleteMovie(Long movieId) {

    }
}
