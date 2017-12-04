package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.domain.Media;
import com.epsi.mediatheque.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class MovieControllerTest extends AbstractMvcSetup {



	@Autowired
	private MovieController movieController;

	@Autowired
	private MediaService mediaService;

	@Test
	public void controllerInitializedCorrectly() {

		assertThat(movieController).isNotNull();
	}

	@Test
	public void getAllMovies() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/movies")
		).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

	}

    @Test
    public void getMovieById() throws Exception{

        MvcResult mvcResult = mockMvc.perform(get("/movies").param("id","3")
        ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        Assert.assertEquals("[{\"id_media\":3,\"type_media\":\"movie\",\"author\":\"Steven Spielberg\",\"title\":\"E.T\",\"creation\":\"1982/12/01\",\"genre\":\"Science-fiction\",\"available\":true}]", mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void addMovie() throws Exception{
        Media mediaTest = new Media();
        mediaTest.setId_media(10);
        mediaTest.setTitle("Titre");
        mediaTest.setType_media("movie");
        mediaTest.setAvailable(true);
        mediaTest.setAuthor("Auteur");
        mediaTest.setCreation("today");
        mediaTest.setGenre("Horreur");

        MvcResult mvcResult = mockMvc.perform(post("/movies")
                .param("id",""+mediaTest.getId_media()).requestAttr("test",mediaTest))
                .andDo(print()).andReturn();
    }

    @Test
    public void borrowMovie() throws Exception{

    }



}
