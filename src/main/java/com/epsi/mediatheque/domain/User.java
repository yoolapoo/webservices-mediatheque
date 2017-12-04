package com.epsi.mediatheque.domain;

import io.swagger.annotations.ApiModelProperty;

public class User {
    @ApiModelProperty(notes = "The database unique user ID")
    private long id_user;
    @ApiModelProperty(notes = "The username of the user")
    private String username;
    @ApiModelProperty(notes = "The email of the user")
    private String email;
    @ApiModelProperty(notes = "The salt of the user password")
    private String salt;
    @ApiModelProperty(notes = "The passhash of the user password")
    private String passhash;
}
