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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "store",description = "Operations about tv shows")
@RestController
public class TvShowController {

    private MediaService tvShowService;
    private LoanService loanService;

    public TvShowController(MediaService tvShowService, LoanService loanService){
        super();
        this.tvShowService = tvShowService;
        this.loanService = loanService;
    }

    /**
     * Get all tv show of the library
     *
     * @return the tv shows
     */
    @ApiOperation(value = "Get all tv shows of the library", response = Media.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("tvshows")
    private List<Media> getTvShows(){
        List<Media> response =this.tvShowService.findAll("tvshow");
        return response;
    }

    /**
     * Get a tv show from its id
     *
     * @param id the id of the wanter tv show
     * @return a tv show with the given id if there is one
     */
    @ApiOperation(value="Get a tv show from its id",response = Media.class, responseContainer = "tv show")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved the tv show"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("tvshows/{id}")
    private Optional<Media> getTvShow(@PathVariable("id") long id){
        Optional<Media> response = this.tvShowService.findById(id);
        if(response.isPresent()){
            return Optional.of(response.get());
        }
        return Optional.empty();
    }

    /**
     * Add a tv show with the given ISBN
     *
     * @param isbn the ISBN
     * @return the id of the added tv show if the isbn exists
     */
    @ApiOperation(value="Add a tv show with the given ISBN",response = String.class, responseContainer = "string")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully added the tv show"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("tvshows/{id}")
    private String addTvShow(@PathVariable long isbn, @RequestBody Media tvshow){
        Optional<Media> tvshowExisted = this.tvShowService.findById(isbn);
        if(tvshowExisted.isPresent()){
            return String.valueOf(tvshowExisted.get().getId_media());
        }else{
            return this.tvShowService.addMedia(isbn, tvshow);
        }
    }


    /**
     * Borrow a tv show from the library
     *
     * @param id the id of the borrowed tv show
     * @param id_user the name of the user
     * @throws MediaNotFoundException if no tv show in the library has the given id
     * @throws UnavailablemediaException if all tv shows in the library with the given id
     */
    @ApiOperation(value="Borrow a tv show from the library")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully borrowed the tv show"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("borrow/{id}/{id_user}")
    private void borrowTvShow(@PathVariable long id,@PathVariable long id_user)throws MediaNotFoundException, UnavailablemediaException {
        Optional<Media> media = this.tvShowService.findById(id);
        if(!media.isPresent()){
            throw new MediaNotFoundException(HttpStatus.BAD_REQUEST, MediaApiStatus.MEDIA_API_STATUS_1402.toString(), MediaApiStatus.MEDIA_API_STATUS_1402.getReason());
        }
        if(media.get().isAvailable()){
            this.loanService.addLoan(id,id_user);
        } else {
            throw new UnavailablemediaException(HttpStatus.NO_CONTENT, MediaApiStatus.MEDIA_API_STATUS_1401.toString(),MediaApiStatus.MEDIA_API_STATUS_1401.getReason());
        }
    }

    /**
     * Return a tv show back to the library
     *
     * @param id_media       the id of the tv show to borrow
     * @param id_user the name of the user
     * @throws MediaNotFoundException            if no tv show in the library has the given id
     * @throws AllMediasAlreadyReturnedException if all tv shows with the given id are already returned
     */
    @ApiOperation(value="Return a tv show from the library")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully returned the tv show"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("return/{id_user}/{id_media}")
    public void returnMusic(@PathVariable long id_user, @PathVariable long id_media) throws MediaNotFoundException, AllMediasAlreadyReturnedException {
        Optional<Media> media = tvShowService.findById(id_media);
        if (!media.isPresent()) {
            throw new MediaNotFoundException(HttpStatus.BAD_REQUEST, MediaApiStatus.MEDIA_API_STATUS_1402.toString(), MediaApiStatus.MEDIA_API_STATUS_1402.getReason());
        }
        if (media.get().isAvailable()) {
            media.get().setAvailable(true);
            tvShowService.updateMedia(media.get());
            loanService.deleteLoan(id_user, id_media);
        } else {
            throw new AllMediasAlreadyReturnedException(HttpStatus.ALREADY_REPORTED, MediaApiStatus.MEDIA_API_STATUS_1403.toString(), MediaApiStatus.MEDIA_API_STATUS_1401.getReason());
        }

    }

    /**
     * Return all tv shows with an author, a title or an ISBN matching the search term
     *
     * @param searchTerm the searched term
     * @return the tv shows matching the search term
     */
    @ApiOperation(value="Return all tv shows with an author, a title or an ISBN matching the search term")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully returned the list of tv shows"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("alltvshows/{searchterm}")
    public List<Media> searchTvShows(@PathVariable String searchTerm) {
        List<Media> responses = new ArrayList<>();
        tvShowService.search(searchTerm).stream().forEach(item -> {
            responses.add(item);
        });
        return responses;
    }
}
