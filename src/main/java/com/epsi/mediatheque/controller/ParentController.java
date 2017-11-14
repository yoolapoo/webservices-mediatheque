package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.utils.SecurityUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.security.GeneralSecurityException;

import static com.epsi.mediatheque.security.SecurityConstants.TOKEN_PREFIX;

@Slf4j
public abstract class ParentController {

	@Value("${spring.security.authentication.jwt.publickey}")
	protected String publicKey;

	protected String retrieveSubFromToken(String token) {

		token = token.replace(TOKEN_PREFIX, "");
		String sub = null;

		try {
			Claims claims = Jwts.parser().setSigningKey(SecurityUtils.loadPublicKey(publicKey)).parseClaimsJws(token).getBody();
			sub = claims.get("sub", String.class);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException
				| GeneralSecurityException e) {
			e.printStackTrace();
			log.error("Error when retrieving the sub from the token: {} ", e.getMessage());
		}

		return sub;
	}

}
