package com.epsi.mediatheque.service;

import com.epsi.mediatheque.controller.AbstractMvcSetup;
import com.epsi.mediatheque.mapper.MediaMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
public class TokenStoreServiceTest extends AbstractMvcSetup {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaMapper mediaMapper;

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(mediaService).isNotNull();
    }


}
