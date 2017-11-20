package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.domain.Media;
import com.epsi.mediatheque.service.MediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "store",description = "Operations about movies")
@RestController
public class MovieController {

    private MediaService movieService;

    public MovieController(MediaService movieService){
        super();
        this.movieService = movieService;
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
     * Get a music from its id
     *
     * @param id the id of the wanter music
     * @return a music with the given id if there is one
     */
    @ApiOperation(value="Get a music from its id",response = Media.class, responseContainer = "movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved the movie"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("movie/{id}")
    private Media getMovie(@PathVariable String id){
        Media response = this.movieService.findById(id);
        return response;
    }

    /**
     * Add a movie with the given ISBN
     *
     * @param isbn the ISBN
     * @return the id of the added music if the isbn exists
     */
    @PutMapping("/{id}")
    private String addMovie(@PathVariable String id){
        return null;
    }


    /**
     * Borrow a movie from the library
     *
     * @param id the id of the borrowed movie
     * @param username the name of the user
     * @throws MediaNotFoundException if no music in the library has the given id
     * @throws UnavailableMediaException if all movies in the library with the given id

     */
    private void borrowMovie(String id, String username) throws MediaNotFoundException, UnavailablemediaException{

    }
}
