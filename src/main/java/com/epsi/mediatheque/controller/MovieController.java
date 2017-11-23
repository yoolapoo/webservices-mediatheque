package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.domain.Media;
import com.epsi.mediatheque.exception.MediaNotFoundException;
import com.epsi.mediatheque.exception.UnavailablemediaException;
import com.epsi.mediatheque.service.LoanService;
import com.epsi.mediatheque.service.MediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Api(value = "store",description = "Operations about movies")
@RestController
public class MovieController {

    private MediaService movieService;
    private LoanService loanService;

    public MovieController(MediaService movieService, LoanService loanService){
        super();
        this.movieService = movieService;
        this.loanService = loanService;
    }

    /**
     * Get all movies of the library
     *
     * @return the movies
     */
    @ApiOperation(value = "Get all movies of the library", response = Media.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("movies")
    private List<Media> getMovies(){
        List<Media> response =this.movieService.findAll("movie");
        return response;
    }

    /**
     * Get a movie from its id
     *
     * @param id the id of the wanter movie
     * @return a movie with the given id if there is one
     */
    @ApiOperation(value="Get a movie from its id",response = Media.class, responseContainer = "movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved the movie"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("movies/{id}")
    private Optional<Media> getMovie(@PathVariable("id") String id){
        Optional<Media> response = this.movieService.findById(id);
        if(response.isPresent()){
            return Optional.of(response.get());
        }
        return Optional.empty();
    }

    /**
     * Add a movie with the given ISBN
     *
     * @param isbn the ISBN
     * @return the id of the added movie if the isbn exists
     */
    @ApiOperation(value="Add a movie with the given ISBN",response = String.class, responseContainer = "string")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully added the movie"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("movies/{id}")
    private String addMovie(@PathVariable String id, @RequestBody Media movie){
        Optional<Media> movieExisted = this.movieService.findById(id);
        if(movieExisted.isPresent()){return String.valueOf(movieExisted.get().getId_media());}
        else{return this.movieService.addMedia(id, movie);}
    }


    /**
     * Borrow a movie from the library
     *
     * @param id_media the id of the borrowed movie
     * @param id_user the identifiant of the user
     * @throws MediaNotFoundException if no movie in the library has the given id
     * @throws UnavailablemediaException if all movies in the library with the given id
     */
    @ApiOperation(value="Borrow a movie from the library")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully borrowed the movie"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("movies")
    private void borrowMovie(long id_user, long id_media) throws MediaNotFoundException, UnavailablemediaException {


        this.loanService.addLoan(id_media,id_user);
    }
}
