package com.epsi.mediatheque.controller;

import java.security.GeneralSecurityException;
import java.util.UUID;

import com.epsi.mediatheque.data.MediathequeApiStatus;
import com.epsi.mediatheque.exception.DafReportPortalApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epsi.mediatheque.dto.TokenDto;
import com.epsi.mediatheque.dto.TokenResponse;
import com.epsi.mediatheque.dto.UrlResponse;
import com.epsi.mediatheque.security.SecurityConstants;
import com.epsi.mediatheque.service.TokenStoreService;
import com.epsi.mediatheque.utils.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

    @Value("${spring.security.authentication.jwt.publickey}")
    private String publicKey;

    @Value("${daf-report-api.dashboard.url}")
    private String dashboardUrl;

    @Value("${spring.security.authentication.jwt.expiration:21600}")
    private long delayExpirationToken;

    private TokenStoreService tokenStoreService;

    public LoginController(TokenStoreService tokenStoreService) {
        this.tokenStoreService = tokenStoreService;
    }

    /**
     * Retrieve a {@link TokenDto} which wrap a JWT, validate it and store it with @{@link TokenStoreService} with an generated uuid
     *
     * @param tokenDto tokenDto
     * @return a {@link UrlResponse}
     * @throws GeneralSecurityException exception if JWT's validation failed
     */
    @PostMapping("/login")
    private UrlResponse login(@RequestBody TokenDto tokenDto) throws GeneralSecurityException {
        String token = tokenDto.getToken();
        Jws<Claims> claims = SecurityUtils.validateToken(publicKey, token.replace(SecurityConstants.TOKEN_PREFIX, ""), delayExpirationToken);
        log.info("valid token received for {}", claims.getBody().getSubject());
        // persist an entry uuid / token
        String someUuid = UUID.randomUUID().toString();
        tokenStoreService.storeToken(someUuid, token);
        log.info("token stored with id: {}", someUuid);
        UrlResponse urlResponse = new UrlResponse();
        urlResponse.setUrl(dashboardUrl + "/login?id=" + someUuid);
        return urlResponse;

    }

    /**
     * Get stored JWT from its uuid provided as form parameter.
     *
     * @param formParams formParams
     * @return a {@link TokenResponse} which contains the JWT
     */
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private TokenResponse getToken(@RequestBody MultiValueMap<String, String> formParams) throws DafReportPortalApiException {
        TokenResponse tokenResponse = new TokenResponse();
        String uuid = formParams.get("username").get(0);
        String token = tokenStoreService.getToken(uuid);
        if (token != null) {
            log.info("token found for uuid: {}", uuid);
            tokenResponse.setAccessToken(token);
            return tokenResponse;
        } else {
            throw new DafReportPortalApiException(
                    HttpStatus.BAD_REQUEST,
                    MediathequeApiStatus.DAF_REPORT_API_STATUS_1400.toString(),
                    MediathequeApiStatus.DAF_REPORT_API_STATUS_1400.getReason());
        }
    }
}
