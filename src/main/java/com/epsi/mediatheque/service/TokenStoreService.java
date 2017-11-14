package com.epsi.mediatheque.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenStoreService {

	String getToken(final String uuid);

	void storeToken(final String uuid, final String token);
}
