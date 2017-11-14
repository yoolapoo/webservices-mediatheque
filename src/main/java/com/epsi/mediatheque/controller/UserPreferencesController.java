package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.domain.UserPreference;
import com.epsi.mediatheque.dto.DafDataResponse;
import com.epsi.mediatheque.dto.DafListResponse;
import com.epsi.mediatheque.service.UserPreferencesService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.util.List;

import static com.epsi.mediatheque.security.SecurityConstants.HEADER_STRING;

@RestController
@Slf4j
public class UserPreferencesController extends ParentController{

    @Value("#{'${listUserPreferencesAllow}'.split(',')}")
    private List<String> listUserPreferencesAllow;

    private UserPreferencesService userPreferencesService;


    public UserPreferencesController(UserPreferencesService userPreferencesService){this.userPreferencesService = userPreferencesService;}


    @GetMapping("userPreferences")
    private DafListResponse getUserPreferences(@RequestHeader(value = HEADER_STRING) String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException, GeneralSecurityException{
        DafListResponse response = new DafListResponse();

        String id = retrieveSubFromToken(token);

        userPreferencesService.findById(id).forEach(c -> {
            DafDataResponse dafDataResponse = new DafDataResponse();
            dafDataResponse.setId(c.getId());
            dafDataResponse.setType("user-preference");
            dafDataResponse.setAttributes(c);
            response.getData().add(dafDataResponse);
        });

        return response;
    }

    @PostMapping("userPreferences")
    private void createUserPreference(@RequestHeader(value = HEADER_STRING) String token, @RequestParam("key") String key, @RequestParam("value") String value ) throws ExpiredJwtException, UnsupportedJwtException,
    MalformedJwtException, SignatureException, IllegalArgumentException, GeneralSecurityException{

        String id = retrieveSubFromToken(token);

        List<UserPreference> listUserPreferences = userPreferencesService.findById(id);

        listUserPreferences.forEach(c->{
            if(listUserPreferencesAllow.contains(key)) {
                if (c.getKey().equals(key)) {
                    userPreferencesService.updateUserPreference(id, key, value);
                } else {
                    userPreferencesService.createUserPreference(id, key, value);
                }
            }else{
                log.info("The key "+key+" not exist in the list, impossible record action");
            }

        });
    }
}
