package org.mindswap.controller;
import jakarta.annotation.security.RolesAllowed;
import org.mindswap.dto.MovieCreateDto;
import org.mindswap.dto.MovieDto;
import org.mindswap.dto.MovieUpdateDto;
import org.mindswap.model.Movie;
import org.mindswap.model.Role;
import org.mindswap.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieCreateDto movieCreateDto) {
        return new ResponseEntity<>(movieService.createMovie(movieCreateDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("{id}")Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id),HttpStatus.OK);
    }

    @GetMapping(path ="/available")
    @PreAuthorize("hasAnyAuthority('CLIENT','WORKER','MANAGER','ADMIN')")
    public ResponseEntity<List<MovieDto>> getAvailableMovies() {
        return new ResponseEntity<List<MovieDto>>(movieService.getAvailableMovies(),HttpStatus.OK);
    }
    @GetMapping(path ="/all")
    @PreAuthorize("hasAnyAuthority('CLIENT','WORKER','MANAGER','ADMIN')")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<MovieDto>  updateMovie(@PathVariable("{id}")Long id, @RequestBody MovieUpdateDto movieUpdateDto) {
        return new ResponseEntity<>(movieService.updateMovie(id,movieUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable("{id}")Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie has been Deleted",HttpStatus.OK);
    }
}
