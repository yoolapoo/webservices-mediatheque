package com.epsi.mediatheque.config;

import com.epsi.mediatheque.mapper.MediaMapper;
import com.epsi.mediatheque.service.MediaService;
import com.epsi.mediatheque.service.impl.JdbcMediaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaConfig {

	@Bean
	public MediaService jdbcMediaService(MediaMapper mediaMapper) {
		return new JdbcMediaServiceImpl(mediaMapper);
	}



}
