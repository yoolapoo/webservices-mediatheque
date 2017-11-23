package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.domain.Media;
import com.epsi.mediatheque.exception.MediaNotFoundException;
import com.epsi.mediatheque.exception.UnavailablemediaException;
import com.epsi.mediatheque.service.MediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "store",description = "Operations about tv shows")
@RestController
public class TvShowController {

    private MediaService tvShowService;

    public TvShowController(MediaService tvShowService){
        super();
        this.tvShowService = tvShowService;
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
    private Optional<Media> getTvShow(@PathVariable("id") String id){
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
    private String addTvShow(@PathVariable String id, @RequestBody Media tvshow){
        Optional<Media> tvshowExisted = this.tvShowService.findById(id);
        if(tvshowExisted.isPresent()){
            return String.valueOf(tvshowExisted.get().getId_media());
        }else{
            return this.tvShowService.addMedia(id, tvshow);
        }
    }


    /**
     * Borrow a tv show from the library
     *
     * @param id the id of the borrowed tv show
     * @param username the name of the user
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
    @PostMapping("tvshows")
    private void borrowTvShow(String id, String username)throws MediaNotFoundException, UnavailablemediaException {

    }
}
