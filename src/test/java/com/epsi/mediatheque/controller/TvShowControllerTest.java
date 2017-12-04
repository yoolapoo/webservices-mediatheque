package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.AbstractMvcTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TvShowControllerTest extends AbstractMvcTest {

    @Autowired
    private TvShowController tvShowController;

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(tvShowController).isNotNull();
    }
}
