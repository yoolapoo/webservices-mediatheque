package com.epsi.mediatheque.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TvShowControllerTest extends AbstractMvcSetup {

    @Autowired
    private TvShowController tvShowController;

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(tvShowController).isNotNull();
    }
}
