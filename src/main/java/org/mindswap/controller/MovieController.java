package org.mindswap.controller;
import org.mindswap.dto.MovieDto;
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

    @GetMapping(path = "/create/{imdbId}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<String> createMovie(@PathVariable("imdbId") Long id) {
        movieService.createMovie(id);
        return new ResponseEntity<>("Movie has been Created", HttpStatus.CREATED);
    }

    @PostMapping(path = "/createMovies")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<String> createMovies(@RequestBody List<Integer> movieIds) {
        List<Long> ids = movieIds.stream().map(Long::valueOf).toList();
        movieService.createMovies(ids);
        return new ResponseEntity<>("Movies have been Created", HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN','WORKER')")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id),HttpStatus.OK);
    }

    @GetMapping(path ="/available")
    public ResponseEntity<List<MovieDto>> getAvailableMovies() {
        List<MovieDto> list = movieService.getAvailableMovies();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping(path ="/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }



    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable("{id}")Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie has been Deleted",HttpStatus.OK);
    }
}