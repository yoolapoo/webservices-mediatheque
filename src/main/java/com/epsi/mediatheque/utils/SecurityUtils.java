package com.epsi.mediatheque.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
public class SecurityUtils {

	public static PublicKey loadPublicKey(String stored) throws GeneralSecurityException {
		byte[] data = Base64.decodeBase64(stored);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		return fact.generatePublic(spec);
	}

	public static Jws<Claims> validateToken(String publicKey, String token, long delayExpirationToken) throws GeneralSecurityException, MalformedJwtException {

		Jws<Claims> claims;

		//parse the token
		claims = Jwts.parser().setSigningKey(loadPublicKey(publicKey)).parseClaimsJws(token);
		//check if token has expired
		if (isNotExpiredToken(claims, delayExpirationToken)) {
			return claims;
		} else {
			throw new GeneralSecurityException("expired token");
		}


	}

	private static boolean isNotExpiredToken(Jws<Claims> claims, long delayExpirationToken) {
		if (claims.getBody() != null && claims.getBody().getIssuedAt() != null) {
			Instant creationToken = claims.getBody().getIssuedAt().toInstant();
			Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);
			Instant expireDateTimeToken = creationToken.plusSeconds(delayExpirationToken);
			return expireDateTimeToken.isAfter(now);
		} else {
			throw new MalformedJwtException("missing attribute in token");
		}
	}
}
