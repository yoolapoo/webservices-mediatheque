package com.epsi.mediatheque.config;

import com.epsi.mediatheque.mapper.FactUserReportsMapper;
import com.epsi.mediatheque.mapper.UserPreferencesMapper;
import com.epsi.mediatheque.mapper.UserReportsMapper;
import com.epsi.mediatheque.service.FactUserReportsService;
import com.epsi.mediatheque.service.UserPreferencesService;
import com.epsi.mediatheque.service.UserReportsService;
import com.epsi.mediatheque.service.impl.JdbcFactUserReportsServiceImpl;
import com.epsi.mediatheque.service.impl.JdbcUserPreferencesServiceImpl;
import com.epsi.mediatheque.service.impl.JdbcUserReportsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DafReportsConfig {

	@Bean
	public FactUserReportsService jdbcFactUserReportsService(FactUserReportsMapper factUserReportsMapper) {
		return new JdbcFactUserReportsServiceImpl(factUserReportsMapper);
	}
	
	@Bean
	public UserReportsService jdbcUserReportsService(UserReportsMapper userReportsMapper) {
		return new JdbcUserReportsServiceImpl(userReportsMapper);
	}
	@Bean
	public UserPreferencesService jdbcUserPreferencesService(UserPreferencesMapper userPreferencesMapper){
		return new JdbcUserPreferencesServiceImpl(userPreferencesMapper);
	}
}
