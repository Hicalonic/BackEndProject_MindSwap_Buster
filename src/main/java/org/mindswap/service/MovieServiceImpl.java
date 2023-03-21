package org.mindswap.service;

import org.mindswap.dto.MovieCreateDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.exceptions.MovieNotFoundException;
import org.mindswap.mapper.MovieMapper;
import org.mindswap.model.Movie;
import org.mindswap.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    private MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }



    @Override
    public MovieDto createMovie(MovieCreateDto movieCreateDto) {
       Movie movie =  movieMapper.fromCreateDtoToEntity(movieCreateDto);
       movieRepository.save(movie);
       return movieMapper.fromEntityToDto(movie);
    }

    @Override
    public List<MovieDto> createMovies(List<MovieCreateDto> movieCreateDtoList) {
        List<Movie> moviesList =  movieCreateDtoList.stream().map(movie -> movieMapper.fromCreateDtoToEntity(movie)).toList();
        moviesList.forEach(movie -> movieRepository.save(movie));
        return moviesList.stream().map(movie -> movieMapper.fromEntityToDto(movie)).toList();
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
      Optional<Movie> movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException);

       return movieMapper.fromEntityToDto(movie);
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
