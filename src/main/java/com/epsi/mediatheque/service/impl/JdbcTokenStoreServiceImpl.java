package com.epsi.mediatheque.service.impl;

import com.epsi.mediatheque.domain.Token;
import com.epsi.mediatheque.mapper.TokenMapper;
import com.epsi.mediatheque.service.TokenStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

/**
 * Greenplum/MyBatis implementation for @{@link TokenStoreService}
 */
@Slf4j
public class JdbcTokenStoreServiceImpl implements TokenStoreService {

	@Value("${daf-report-api.tokenstore.token-expiration-time:600}")
	private long tokenExpirationTime;

	private TokenMapper tokenMapper;

	public JdbcTokenStoreServiceImpl(TokenMapper tokenMapper) {
		this.tokenMapper = tokenMapper;
	}

	public String getToken(final String uuid) {
		Token token = tokenMapper.findByUuid(uuid);

		if (token != null && token.getExpireAt() != null) {
			if(token.getExpireAt().isAfter(LocalDateTime.now())) {
				//valid token
				return token.getToken();
			}
		}
		log.info("no token found for uuid: {}", uuid);
		return null;
	}

	public void storeToken(final String uuid, final String tokenStr) {
		Token token = new Token();
		token.setUuid(uuid);
		token.setToken(tokenStr);
		token.setExpireAt(LocalDateTime.now().plusSeconds(tokenExpirationTime));
		tokenMapper.storeToken(token);
	}
}
