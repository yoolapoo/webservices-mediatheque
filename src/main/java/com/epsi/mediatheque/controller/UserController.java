package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.dto.DafResponse;
import com.epsi.mediatheque.utils.SecurityUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.GeneralSecurityException;

import static com.epsi.mediatheque.security.SecurityConstants.HEADER_STRING;
import static com.epsi.mediatheque.security.SecurityConstants.TOKEN_PREFIX;

@RestController
public class UserController {

	@Value("${spring.security.authentication.jwt.publickey}")
	private String publicKey;

	@GetMapping("userinfo")
	private DafResponse	userInfo(@RequestHeader(value = HEADER_STRING) String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException, GeneralSecurityException {
		
		token = token.replace(TOKEN_PREFIX, "");
		Claims claims = Jwts.parser().setSigningKey(SecurityUtils.loadPublicKey(publicKey)).parseClaimsJws(token).getBody();
		DafResponse response = new DafResponse();
		response.getData().setId("userinfo");
		response.getData().setType("userinfo");
		response.getData().setAttributes(claims);
		return response;
	}
}
