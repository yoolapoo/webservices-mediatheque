package com.epsi.mediatheque.config;

import com.epsi.mediatheque.mapper.TokenMapper;
import com.epsi.mediatheque.service.TokenStoreService;
import com.epsi.mediatheque.service.impl.JdbcTokenStoreServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenStoreConfig {

	@Bean
	@ConditionalOnProperty(prefix = "webservices-mediatheque-api.tokenstore", name="implementation", havingValue = "jdbc")
	public TokenStoreService jdbcTokenStoreService(TokenMapper tokenMapper) {
		return new JdbcTokenStoreServiceImpl(tokenMapper);
	}


}
