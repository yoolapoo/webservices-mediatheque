package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.UserPreference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPreferencesService {

    List<UserPreference> findById(String id);

    void createUserPreference(final String id, final String key, final String value);

    void updateUserPreference(final String id, final String key, final String value);
}
