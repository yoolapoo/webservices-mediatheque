package com.epsi.mediatheque.security;


import com.epsi.mediatheque.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import static com.epsi.mediatheque.security.SecurityConstants.HEADER_STRING;
import static com.epsi.mediatheque.security.SecurityConstants.TOKEN_PREFIX;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private String publicKey;
	private long delayExpirationToken;	

	public JWTAuthorizationFilter(AuthenticationManager authManager, String publicKey, long delayExpirationToken) {
		super(authManager);
		this.publicKey = publicKey;
		this.delayExpirationToken = delayExpirationToken;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
	                                HttpServletResponse res,
	                                FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = null;
			try {
				Jws<Claims> claims = SecurityUtils.validateToken(publicKey,token.replace(TOKEN_PREFIX, ""),delayExpirationToken);
				user = claims.getBody().getSubject();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

	private class HEADER_STRING {
	}
}
