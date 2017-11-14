package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.dto.DafDataResponse;
import com.epsi.mediatheque.dto.DafListResponse;
import com.epsi.mediatheque.service.FactUserReportsService;
import com.epsi.mediatheque.utils.SecurityUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.epsi.mediatheque.security.SecurityConstants.HEADER_STRING;
import static com.epsi.mediatheque.security.SecurityConstants.TOKEN_PREFIX;

@RestController
public class FactUserReportsController {

	private FactUserReportsService factUserReportsService;

	@Value("${spring.security.authentication.jwt.publickey}")
	private String publicKey;

	public FactUserReportsController(FactUserReportsService factUserReportsService) {
		super();
		this.factUserReportsService = factUserReportsService;
	}

	@GetMapping("factUserReports")
	private DafListResponse factUserReports(@RequestHeader(value = HEADER_STRING) String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException, GeneralSecurityException {
		DafListResponse response = new DafListResponse();

		token = token.replace(TOKEN_PREFIX, "");
		Claims claims = Jwts.parser().setSigningKey(SecurityUtils.loadPublicKey(publicKey)).parseClaimsJws(token).getBody();
		String id = claims.get("sub", String.class);

	    AtomicInteger counter = new AtomicInteger(0);
		factUserReportsService.findById(id).forEach(c -> {
			DafDataResponse dafDataResponse = new DafDataResponse();
			dafDataResponse.setId(c.getId() + counter.getAndIncrement());
			dafDataResponse.setType("fact-user-report");
			dafDataResponse.setAttributes(c);
			response.getData().add(dafDataResponse);
		});

		return response;
	}
}
