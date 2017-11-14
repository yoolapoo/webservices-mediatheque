package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.AbstractMvcTest;
import com.epsi.mediatheque.domain.UserPreference;
import com.epsi.mediatheque.dto.DafDataResponse;
import com.epsi.mediatheque.dto.DafListResponse;
import com.epsi.mediatheque.service.UserPreferencesService;
import com.epsi.mediatheque.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Slf4j
public class UserPreferencesControllerTest extends AbstractMvcTest {

    @Value("${spring.security.authentication.jwt.publickey}")
    private String publicKey;

    @Value("#{'${listUserPreferencesAllow}'.split(',')}")
    private List<String> listUserPreferencesAllow;

    @Autowired
    private UserPreferencesService userPreferencesService;

    @Autowired
    private UserPreferencesController userPreferencesController;


    @Test
    public void controllerInitializedCorrectly() {

        assertThat(userPreferencesController).isNotNull();
    }

    @Test
    public void getDataUserPreferences() throws Exception{
        DafListResponse response = new DafListResponse();
        String token = generateValidToken();
        Claims claims = Jwts.parser().setSigningKey(SecurityUtils.loadPublicKey(publicKey)).parseClaimsJws(token).getBody();
        String id = claims.get("sub", String.class);
       List<UserPreference> pref = userPreferencesService.findById(id);

       pref.forEach(c-> {
           DafDataResponse dafDataResponse = new DafDataResponse();
           dafDataResponse.setId(c.getId());
           dafDataResponse.setType("user-preferences");
           dafDataResponse.setAttributes(c);
           response.getData().add(dafDataResponse);

           Assert.assertEquals(id,c.getId());
       });

    }

    @Test
    public void saveDataUserPreference() throws Exception {

        String token = generateValidToken();
        Claims claims = Jwts.parser().setSigningKey(SecurityUtils.loadPublicKey(publicKey)).parseClaimsJws(token).getBody();
        String id = claims.get("sub", String.class);
        String key = "lol";
        String value = "200";

        List<UserPreference> listUserPreferences = userPreferencesService.findById(id);
        log.info("Number of UserPreferences: " + listUserPreferences.size());

        if(listUserPreferences.isEmpty()){
            userPreferencesService.createUserPreference(id, key, value);
            log.info("record action done !");
        }else {
            listUserPreferences.forEach(c -> {
                if (listUserPreferencesAllow.contains(key)) {
                    if (c.getKey().equals(key)) {
                        userPreferencesService.updateUserPreference(id, key, value);
                        log.info("update action done !");
                    } else {
                        userPreferencesService.createUserPreference(id, key, value);
                        log.info("record action done !");
                    }
                } else {
                    log.info("The key " + key + " not exist in the list, impossible record action");
                }

            });
        }
        log.info("Number of UserPreferences: " + listUserPreferences.size());
    }

}

