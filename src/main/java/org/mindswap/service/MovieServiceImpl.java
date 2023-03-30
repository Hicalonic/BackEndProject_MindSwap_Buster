package org.mindswap.service;

import org.mindswap.dto.MovieDto;
import org.mindswap.exceptions.MovieNotFoundException;
import org.mindswap.mapper.MovieMapper;
import org.mindswap.model.Movie;
import org.mindswap.repository.MovieRepository;
import org.mindswap.utils.IMDBAPI.ImdbMovieModel;
import org.mindswap.utils.IMDBAPI.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private MovieMapper movieMapper;
    private ImdbService imdbService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, ImdbService imdbService) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.imdbService = imdbService;
    }



    @Override
    public void createMovie(Long id) {
        ImdbMovieModel imdbMovieModel = imdbService.getImdbMovieById(id);

        Movie movie =  movieMapper.fromImdbMovieModelToMovie(imdbMovieModel);
        movie.setPrice(generatePrice(movie.getImdbRating()));
        movie.setAvailable(true);


        movieRepository.save(movie);
    }

    @Override
    public void createMovies(List<Long> imdbIds) {
        List<ImdbMovieModel> moviesFromImdbRep = imdbService.getMoviesById(imdbIds);
        List<Movie> movies = moviesFromImdbRep.stream()
                .map(movie->movieMapper.fromImdbMovieModelToMovie(movie)).toList();
        movies.forEach(movie -> movie.setPrice(generatePrice(movie.getImdbRating())));
        movies.forEach(movie -> movie.setAvailable(true));
        movies.forEach(movie -> movieRepository.save(movie));
    }

    @Override
    public double generatePrice(double rating) {
        double minPrice = 5;
        double mediumPrice = 15;
        return  minPrice + (mediumPrice * (rating / 10));
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        return movieMapper.fromEntityToDto(movie);
    }

    @Override
    public List<MovieDto> getAvailableMovies() {
        List<Movie> moviesAvailable = movieRepository.findAll().stream().filter(movie -> movie.isAvailable()).toList();
        return moviesAvailable.stream().map(movie -> movieMapper.fromEntityToDto(movie)).toList();
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movie -> movieMapper.fromEntityToDto(movie)).toList();
    }



    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        movieRepository.delete(movie);
    }



}