package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.data.MediaApiStatus;
import com.epsi.mediatheque.domain.Media;
import com.epsi.mediatheque.exception.AllMediasAlreadyReturnedException;
import com.epsi.mediatheque.exception.MediaNotFoundException;
import com.epsi.mediatheque.exception.UnavailablemediaException;
import com.epsi.mediatheque.service.LoanService;
import com.epsi.mediatheque.service.MediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "store",description = "Operations about movies")
@RestController
public class MovieController {

    private MediaService movieService;
    private LoanService loanService;

    public MovieController(MediaService mediaService, LoanService loanService){
        super();
        this.movieService = mediaService;
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
    @GetMapping(value = "movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(value = "movies/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Optional<Media> getMovie(@PathVariable("id") long id){
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
    @PostMapping(value = "movies/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private String addMovie(@PathVariable long isbn, @RequestBody Media movie){
        Optional<Media> movieExisted = this.movieService.findById(isbn);
        if(movieExisted.isPresent()){return String.valueOf(movieExisted.get().getId_media());}
        else{return this.movieService.addMedia(isbn, movie);}
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
    @PostMapping(value = "movieborrow/{id_user}/{id_media}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private void borrowMovie(@PathVariable long id_user, @PathVariable long id_media) throws MediaNotFoundException, UnavailablemediaException {
        Optional<Media> media = this.movieService.findById(id_media);
        if(!media.isPresent()){
            throw new MediaNotFoundException(HttpStatus.BAD_REQUEST, MediaApiStatus.MEDIA_API_STATUS_1402.toString(), MediaApiStatus.MEDIA_API_STATUS_1402.getReason());
        }
        if(media.get().isAvailable()){
            this.loanService.addLoan(id_media,id_user);
        } else {
            throw new UnavailablemediaException(HttpStatus.NO_CONTENT, MediaApiStatus.MEDIA_API_STATUS_1401.toString(),MediaApiStatus.MEDIA_API_STATUS_1401.getReason());
        }

    }

    /**
     * Return a movie back to the library
     *
     * @param id_media       the id of the movie to borrow
     * @param id_user the name of the user
     * @throws MediaNotFoundException            if no movie in the library has the given id
     * @throws AllMediasAlreadyReturnedException if all movies with the given id are already returned
     */
    @ApiOperation(value="Return a movie from the library")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully returned the movie"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "moviereturn/{id_user}/{id_media}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void returnMusic(@PathVariable long id_user, @PathVariable long id_media) throws MediaNotFoundException, AllMediasAlreadyReturnedException {
        Optional<Media> media = movieService.findById(id_media);
        if (!media.isPresent()) {
            throw new MediaNotFoundException(HttpStatus.BAD_REQUEST, MediaApiStatus.MEDIA_API_STATUS_1402.toString(), MediaApiStatus.MEDIA_API_STATUS_1402.getReason());
        }
        if (media.get().isAvailable()) {
            media.get().setAvailable(true);
            movieService.updateMedia(media.get());
            loanService.deleteLoan(id_user, id_media);
        } else {
            throw new AllMediasAlreadyReturnedException(HttpStatus.ALREADY_REPORTED, MediaApiStatus.MEDIA_API_STATUS_1403.toString(), MediaApiStatus.MEDIA_API_STATUS_1401.getReason());
        }

    }

    /**
     * Return all movies with an author, a title or an ISBN matching the search term
     *
     * @param searchTerm the searched term
     * @return the movies matching the search term
     */
    @ApiOperation(value="Return all movies with an author, a title or an ISBN matching the search term")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully returned the list of movies"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "allmovies/{searchterm}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Media> searchMovies(@PathVariable String searchTerm) {
        List<Media> responses = new ArrayList<>();
        movieService.search(searchTerm).stream().forEach(item -> {
            responses.add(item);
        });
        return responses;
    }




}
