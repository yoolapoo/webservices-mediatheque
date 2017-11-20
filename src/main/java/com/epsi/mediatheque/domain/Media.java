package com.epsi.mediatheque.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Media {

    @ApiModelProperty(notes = "The database unique media ID")
    private String id_media;
    @ApiModelProperty(notes = "Type of the media")
    private String type_media;
    @ApiModelProperty(notes = "Author of the media")
    private String author;
    @ApiModelProperty(notes = "Title of the media")
    private String title;
    @ApiModelProperty(notes = "Created Date of the media")
    private String creation;
    @ApiModelProperty(notes = "Genre of the media")
    private String genre;
}
