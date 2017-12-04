package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.AbstractMvcTest;
import com.epsi.mediatheque.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Slf4j
public class MovieControllerTest extends AbstractMvcTest {

/*	@Value("${daf-report-api.dashboard.url}")
	private String dashboardUrl;*/

	@Autowired
	private MovieController movieController;

	@Autowired
	private MediaService mediaService;

	@Test
	public void controllerInitializedCorrectly() {

		assertThat(movieController).isNotNull();
	}



	/*@Test
	public void loginWithValidToken() throws Exception {
		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(generateValidToken());
		MvcResult mvcResult = mockMvc.perform(post("/login")
			.contentType(MediaType.APPLICATION_JSON_UTF8).content(json(tokenDto)))
			.andExpect(status().isOk())
			.andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)))
			.andExpect(jsonPath("$.url", Matchers.startsWith(dashboardUrl)))
			.andReturn();

		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		URL url = new URL(jsonObject.getString("url"));
		Map<String, String> urlParams = splitQuery(url);
		Assert.assertTrue("Url should contain an 'id' paramter",urlParams.containsKey("id"));

	}

	@Test
	public void loginWithExpiredToken() throws Exception {
		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(generateExpiredToken());
		mockMvc.perform(post("/login")
			.contentType(MediaType.APPLICATION_JSON_UTF8).content(json(tokenDto)))
			.andExpect(status().isForbidden())
			.andExpect(MockMvcResultMatchers.jsonPath(".code").value(String.valueOf(MediathequeApiStatus.DAF_REPORT_API_STATUS_1401.value())));
	}

	@Test
	public void loginWithMalformatedToken() throws Exception {
		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(generateMalformedToken());
		mockMvc.perform(post("/login")
			.contentType(MediaType.APPLICATION_JSON_UTF8).content(json(tokenDto)))
			.andExpect(status().isForbidden())
			.andExpect(MockMvcResultMatchers.jsonPath(".code").value(String.valueOf(MediathequeApiStatus.DAF_REPORT_API_STATUS_1402.value())));
	}

	@Test
	public void requestTokenWithInvalidFormParams() throws Exception {

		mockMvc.perform(post("/token")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
			.param("username", "somethingThatDoesNotExist"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath(".code").value(String.valueOf(MediathequeApiStatus.DAF_REPORT_API_STATUS_1400.value())));
	}

	@Test
	public void requestTokenWithValidFormParams() throws Exception {
		String uuid = UUID.randomUUID().toString();
		String aFakeToken = "aFakeToken";
		tokenStoreService.storeToken(uuid, aFakeToken);
		mockMvc.perform(post("/token")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
			.param("username", uuid))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath(".access_token").value(aFakeToken));

	}*/

}