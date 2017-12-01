package com.epsi.mediatheque.domain;

import io.swagger.annotations.ApiModelProperty;

public class Loan {
    @ApiModelProperty(notes = "The database unique loan ID")
    private long id_loan;
    @ApiModelProperty(notes = "The database unique user ID")
    private long id_user;
    @ApiModelProperty(notes = "The database unique media ID")
    private long id_media;
}
